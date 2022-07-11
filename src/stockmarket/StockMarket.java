package swap.bot.root.stockmarket;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static swap.bot.root.Config.*;

public class StockMarketListener extends ListenerAdapter {

    int MIN_STOCK_PRICE = 1, MAX_STOCK_PRICE = 5000, currentMarketPrice = 2500, volatility = 8, oldPrice;
    MessageReceivedEvent event;
    JDA jda;
    public StockMarketListener(){
        ScheduledExecutorService market = Executors.newSingleThreadScheduledExecutor();
        market.scheduleAtFixedRate(this::getNextPrice, 0, 10, TimeUnit.SECONDS);
        ScheduledExecutorService print = Executors.newSingleThreadScheduledExecutor();
        print.scheduleAtFixedRate(this::print, 5, 20, TimeUnit.SECONDS);
    }

    void print(){
        jda.getGuildById(Guild_SWAPBOT)
                .getTextChannelById(Guild_SWAPBOT_Channel_STOCKPRICES)
                .editMessageEmbedsById(976692921329913896L, stockPrice().build()).queue();
    }

    @Override
    public void onReady(ReadyEvent event) {
        this.jda = event.getJDA();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        this.event = event;
        if(args[0].equalsIgnoreCase("!stock"))
            event.getChannel().sendMessageEmbeds(stockPrice().build()).queue();
        if(args[0].equalsIgnoreCase("!volatility") && event.getAuthor().getId().equals("310618714380697612")) {
            changeVol(args[1]);
            event.getMessage().reply("Volatility changed.").queue();
        }
        if(args[0].equalsIgnoreCase("!portfolio")) {
            if (!new File("data/stock/users/" + event.getAuthor().getId()).exists()) {
                createPlayer(event.getAuthor().getId());
                event.getChannel().sendMessageEmbeds(getPortfolio(event.getAuthor().getId(), true).build()).queue();
            } else {
                event.getChannel().sendMessageEmbeds(getPortfolio(event.getAuthor().getId(), false).build()).queue();
            }
        }

        if(args[0].equalsIgnoreCase("!sell")){
            int amount = Integer.parseInt(args[1]);
            if (!new File("data/stock/users/" + event.getAuthor().getId()).exists()) {
                createPlayer(event.getAuthor().getId());
                if(amount > 5) {
                    event.getMessage().replyEmbeds(new EmbedBuilder()
                            .setTitle("Transaction failed")
                            .setColor(Color.RED)
                            .setDescription("You do not have enough shares.")
                            .addField("Shares", "5", false)
                            .addField("Money", "$0", false)
                            .build()).queue();
                } else {
                    int newMoney = currentMarketPrice * amount;
                    int newShares = 5 - amount;
                    writePlayer(event.getAuthor().getId(), newShares, newMoney);
                    event.getMessage().replyEmbeds(new EmbedBuilder()
                            .setTitle("Transaction receipt")
                            .setColor(Color.GREEN)
                            .setDescription("Welcome " + event.getAuthor().getName() + ", type !portfolio to see your wallet. " +
                                    "Then, check back randomly throughout the day (`!stock`) to see if prices go any lower. " +
                                    "If it does, buy more shares. If it goes higher, sell your last 2 shares. " +
                                    "Rinse and repeat and gain profit :^)")
                            .addField("Shares sold", String.valueOf(amount), false)
                            .addField("Profit", "$" + newMoney, false)
                            .addField("Sold at", getStockPrice(), false)
                            .build()).queue();
                }
            } else {
                String[] data = readPlayer(event.getAuthor().getId());
                int money = Integer.parseInt(data[0]);
                int shares = Integer.parseInt(data[1]);
                if(amount > shares){
                    event.getMessage().replyEmbeds(new EmbedBuilder()
                            .setTitle("Transaction failed")
                            .setColor(Color.RED)
                            .setDescription("You do not have enough shares.")
                            .addField("Shares", String.valueOf(shares), false)
                            .addField("Money", "$" + money, false)
                            .build()).queue();
                } else {
                    int newMoney = money + (currentMarketPrice * amount);
                    int newShares = shares - amount;
                    writePlayer(event.getAuthor().getId(), newShares, newMoney);
                    event.getMessage().replyEmbeds(new EmbedBuilder()
                            .setTitle("Transaction receipt")
                            .setColor(Color.GREEN)
                            .addField("Shares sold", String.valueOf(amount), false)
                            .addField("Profit", "$" + currentMarketPrice * amount, false)
                            .addField("Sold at", getStockPrice(), false)
                            .build()).queue();
                }
            }
        }

        if(args[0].equalsIgnoreCase("!buy")){
            int amount = Integer.parseInt(args[1]);
            if (!new File("data/stock/users/" + event.getAuthor().getId()).exists()){
                createPlayer(event.getAuthor().getId());
                event.getMessage().replyEmbeds(new EmbedBuilder()
                        .setTitle("Transaction failed!")
                        .setColor(Color.RED)
                        .setDescription("You don't have any money! To get money, you need to sell some shares. " +
                                "Here's 5 to start with.\nType `!sell 3` to sell 3 out of 5 shares at the the current market price, " +
                                "then type `!portfolio` to see your wallet. Check back randomly throughout the day (`!stock`) " +
                                "to see if prices go any lower. If it does, buy more shares. If it goes higher, sell your last 2 shares. " +
                                "Rinse and repeat to gain profit :^)")
                        .addField("Current Shares", "5", false)
                        .addField("Cash", "$0", false)
                        .addField("Current price", getStockPrice(), false)
                        .build()).queue();
            } else {
                String[] data = readPlayer(event.getAuthor().getId());
                int money = Integer.parseInt(data[0]);
                int shares = Integer.parseInt(data[1]);

                if((amount * currentMarketPrice) > money){
                    event.getChannel().sendMessageEmbeds(new EmbedBuilder()
                            .setTitle("Transaction failed!")
                            .setColor(Color.RED)
                            .setDescription("You don't have enough! Sell shares, or wait until prices go down.")
                            .addField("Current Shares", String.valueOf(shares), false)
                            .addField("Cash", "$" + money, false)
                            .addField("Current price", getStockPrice(), false)
                            .build()).queue();
                } else {
                    int newShares = shares + amount;
                    int newMoney = money - (amount * currentMarketPrice);
                    writePlayer(event.getAuthor().getId(), newShares, newMoney);
                    event.getChannel().sendMessageEmbeds(new EmbedBuilder()
                            .setTitle("Transaction receipt")
                            .setColor(Color.GREEN)
                            .setDescription("You bought " + amount + " shares at " + getStockPrice())
                            .addField("Current Shares", String.valueOf(newShares), false)
                            .addField("Cash", "$" + newMoney, false).build()).queue();
                }
            }
        }
    }
    void getNextPrice1(){
        int crash = new Random().nextInt(500);
        if(crash < 2){
            int amt = new Random().nextInt(1500);
            if(currentMarketPrice < 2500) {
                currentMarketPrice += amt;

            } else if(currentMarketPrice > 2500){
                currentMarketPrice -= amt;
                System.out.println("Downhill! +" + amt);
            }
        }

        int change = new Random().nextInt(volatility - 1) + 1;

        boolean rand = new Random().nextBoolean();
        int newPrice;
        if(rand){
            newPrice = currentMarketPrice + change;
        } else {
            newPrice = currentMarketPrice - change;
        }

        if(newPrice < MIN_STOCK_PRICE){
            newPrice += Math.abs(change) * 2;
        } else if(newPrice > MAX_STOCK_PRICE){
            newPrice -= Math.abs(change) * 2;
        }
        System.out.println(newPrice);
        currentMarketPrice = newPrice;
        //writeLastPrice(currentMarketPrice);
    }

    void getNextBitPrice(){
        //https://www.coinbase.com/price/bitcoin
    }

    void getNextPrice(){
        currentMarketPrice = readLastPrice();
        int crash = new Random().nextInt(300);
        if(crash == 1){
            int amt = new Random().nextInt(1500);
            if(currentMarketPrice < 2500) {
                currentMarketPrice += amt;
                jda.getGuildById(Guild_SWAPBOT)
                        .getTextChannelById(Guild_SWAPBOT_Channel_DIPSANDRISES)
                        .sendMessageEmbeds(new EmbedBuilder()
                                .setTitle("Market rise!")
                                .setColor(Color.green)
                                .addField("Rise amount", "$" + amt, false)
                                .addField("New market price", "$" + currentMarketPrice, false)
                                .build()
                        ).queue();

            } else if(currentMarketPrice > 2500){
                currentMarketPrice -= amt;
                jda.getGuildById(Guild_SWAPBOT)
                        .getTextChannelById(Guild_SWAPBOT_Channel_DIPSANDRISES)
                        .sendMessageEmbeds(new EmbedBuilder()
                                .setTitle("Market crash!")
                                .setColor(Color.red)
                                .addField("Crash amount", "$" + amt, false)
                                .addField("New market price", "$" + currentMarketPrice, false)
                                .build()
                        ).queue();            }
        }

        int change = new Random().nextInt(volatility);

        boolean rand = new Random().nextBoolean();
        int newPrice;
        if(rand){
            newPrice = currentMarketPrice + change;
        } else {
            newPrice = currentMarketPrice - change;
        }

        if(newPrice < MIN_STOCK_PRICE){
            newPrice += Math.abs(change) * 2;
        } else if(newPrice > MAX_STOCK_PRICE){
            newPrice -= Math.abs(change) * 2;
        }
        System.out.println(newPrice);
        currentMarketPrice = newPrice;
        writeLastPrice(currentMarketPrice);
    }

    public String getStockPrice(){
        return "$" + currentMarketPrice;
    }

    public String[] readPlayer(String userID){
        String[] str = null;
        try {
            str = String.valueOf(Files.readString(Path.of("data/stock/users/" + userID))).trim().split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public void writePlayer(String userID, int shares, double money){
        try {
            FileWriter myWriter = new FileWriter("data/stock/users/" + userID);
            myWriter.write(money + "," + shares);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createPlayer(String userID) {
        String fileName = "data/stock/users/" + userID;
        try {
            new File(fileName).createNewFile();
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write("0,5");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EmbedBuilder getPortfolio(String userID, boolean newPlayer) {
        String[] data = readPlayer(userID);
        EmbedBuilder embed = new EmbedBuilder().setTitle(event.getAuthor().getName() + "'s Wallet")
                .setColor(Color.BLUE)
                .addField("Cash", "$" + data[0], false)
                .addField("Shares", data[1], false)
                .setFooter("This game is experimental. Files are ephemeral and you will be reset every 24 hrs.")
                .setAuthor(event.getAuthor().getName());
        if (newPlayer) {
            embed.setDescription("Welcome " + event.getAuthor().getName() + "! Type `!sell 3` to sell your first three shares. " +
                    "Then, check back randomly throughout the day (`!stock`) to see if it goes any lower. If it does, buy more shares. If it " +
                    "goes higher, sell your last 2 shares. Rinse and repeat to gain profit :^)");
        }
        return embed;
    }

    public Integer readLastPrice(){
        String str = null;
        try {
            str = String.valueOf(Files.readString(Path.of("data/stock/price.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert str != null;
        return Integer.parseInt(str);
    }

    public void writeLastPrice(int money){
        try {
            FileWriter myWriter = new FileWriter("data/stock/price.txt");
            myWriter.write("" + money);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EmbedBuilder stockPrice(){
        EmbedBuilder embed =  new EmbedBuilder()
                .setTitle("RootCoin ==> Entry level stocks")
                .setAuthor("Low: $1, High: $5000")
                .setDescription("Stocks update every 10 seconds.")
                .addField("Volatility", "" + volatility, false)
                .addField("Last price", "$" + oldPrice, false)
                .addField("Current price", "$" + currentMarketPrice, false);
        String difference;
        if(currentMarketPrice >= oldPrice) {
            difference = "+" + (currentMarketPrice - oldPrice);
            embed.setColor(Color.GREEN);
        } else {
            difference = "-" + (oldPrice - currentMarketPrice);
            embed.setColor(Color.RED);
        }
        oldPrice = currentMarketPrice;
        embed.addField("Diff", difference, false);
        return embed;
    }

    public void changeVol(String vol){
        try {
            FileWriter myWriter = new FileWriter("data/stock/volatility.txt");
            myWriter.write(vol);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double[] readVolatility(){
        double[] arr = null;
        try {
            String str = String.valueOf(Files.readString(Path.of("data/stock/volatility.txt")));
            arr = Arrays.stream(str.split(","))
                    .mapToDouble(Double::parseDouble)
                    .toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }
}
