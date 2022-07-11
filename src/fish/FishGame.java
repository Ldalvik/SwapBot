package root.ogbot.fish;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import root.ogbot.WeightedTable;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public record FishGame(FishUserData playerData, MessageReceivedEvent event) {
    public static final DecimalFormat df = new DecimalFormat( "#.##" );

    public void getWallet(){
        event.getMessage().replyEmbeds(new EmbedBuilder()
                .setTitle(event.getAuthor().getName() + "'s wallet")
                .addField("Common fish", "" + playerData.getCommonFish(), false)
                .addField("Rare fish", "" + playerData.getRareFish(), false)
                .addField("Exotic fish", "" + playerData.getExoticFish(), false)
                .addField("Cash", "$" + playerData.getCash(), false)
                .build()).queue();
    }

    public void goFish(){
        long timeLeft = playerData.getFishTimer() - System.currentTimeMillis();
        int timeSeconds = new Date(timeLeft).getSeconds();
        if(timeLeft > 0){
            event.getMessage().replyEmbeds(new EmbedBuilder()
                    .setTitle("You are still rebaiting your rod!")
                    .addField("Time left", timeSeconds + " seconds", false)
                    .build()).queue();
        } else {
            playerData.setFishTimer(System.currentTimeMillis() + FishConfig.FISH_TIMER[playerData.PERK_FASTER_FISHING]).save();
            run();
        }
    }


    private void run() {
        WeightedTable<Integer> table = new WeightedTable<>();
        table.add(0, 5);
        table.add(1, 10);
        table.add(2, 15);
        table.add(3, 20);
        table.add(4, 80);
        //table.add(5, 5);

        switch (table.roll()) {
            case 0 -> {
                int cashReward = new Random().nextInt(playerData.getPerkMaxTreasureRewards() - 500) + 500;
                playerData.setCash(playerData().getCash() + cashReward).save();
                event.getMessage().replyEmbeds(new EmbedBuilder()
                        .setColor(Color.YELLOW)
                        .setTitle("You found a chest filled with gold! You sold it for $" + cashReward)
                        .addField("Common fish", "" + playerData.getCommonFish(), false)
                        .addField("Rare fish", "" + playerData.getRareFish(), false)
                        .addField("Exotic fish", "" + playerData.getExoticFish(), false)
                        .addField("Cash", "$" + playerData.getCash(), false)
                        .build()
                ).queue();
            }
            case 1 -> {
                int cashReward = new Random().nextInt(playerData.getPerkMaxTreasureRewards() - 100) + 100;
                playerData.setCash(playerData().getCash() + cashReward).save();
                event.getMessage().replyEmbeds(new EmbedBuilder()
                        .setColor(Color.MAGENTA)
                        .setTitle("You found a suitcase filled with cash, it had $" + cashReward + " inside!")
                        .addField("Common fish", "" + playerData.getCommonFish(), false)
                        .addField("Rare fish", "" + playerData.getRareFish(), false)
                        .addField("Exotic fish", "" + playerData.getExoticFish(), false)
                        .addField("Cash", "$" + playerData.getCash(), false)
                        .build()
                ).queue();
            }
            case 2 -> {
                int fishCaught = new Random().nextInt(
                        playerData.getPerkMaxExoticFish() - 1) + 1 +
                        new Random().nextInt(playerData.getPerkMaxBonusExoticFish());
                playerData.setExoticFish(playerData().getExoticFish() + fishCaught).save();
                event.getMessage().replyEmbeds(new EmbedBuilder()
                        .setColor(Color.yellow)
                        .setTitle("You caught " + fishCaught + " exotic fish!!!")
                        .addField("Common fish", "" + playerData.getCommonFish(), false)
                        .addField("Rare fish", "" + playerData.getRareFish(), false)
                        .addField("Exotic fish", "" + playerData.getExoticFish(), false)
                        .addField("Cash", "$" + playerData.getCash(), false)
                        .build()
                ).queue();
            }
            case 3 -> {
                int fishCaught = new Random().nextInt(
                        playerData.getPerkMaxRareFish() - 1) + 1 +
                        new Random().nextInt(playerData.getPerkMaxBonusRareFish());
                playerData.setRareFish(playerData().getRareFish() + fishCaught).save();
                event.getMessage().replyEmbeds(new EmbedBuilder()
                        .setColor(Color.blue)
                        .setTitle("You caught " + fishCaught + " rare fish!!")
                        .addField("Common fish", "" + playerData.getCommonFish(), false)
                        .addField("Rare fish", "" + playerData.getRareFish(), false)
                        .addField("Exotic fish", "" + playerData.getExoticFish(), false)
                        .addField("Cash", "$" + playerData.getCash(), false)
                        .build()
                ).queue();
            }
            case 4 -> {
                int fishCaught = new Random().nextInt(
                        playerData.getPerkMaxCommonFish() - 1) + 1 +
                        new Random().nextInt(playerData.getPerkMaxBonusCommonFish());
                playerData.setCommonFish(playerData().getCommonFish() + fishCaught).save();
                event.getMessage().replyEmbeds(new EmbedBuilder()
                        .setColor(Color.green)
                        .setTitle("You caught " + fishCaught + " common fish!")
                        .addField("Common fish", "" + playerData.getCommonFish(), false)
                        .addField("Rare fish", "" + playerData.getRareFish(), false)
                        .addField("Exotic fish", "" + playerData.getExoticFish(), false)
                        .addField("Cash", "$" + playerData.getCash(), false)
                        .build()
                ).queue();
            }
        }
    }

    public void sellCommonFish(int amount) {
        if (playerData.getCommonFish() < amount) {
            event.getMessage().reply("You don't have that many fish!").queue();
        } else {
            double priceSoldFor = amount * Double.parseDouble(getFishPrices().get(0)) *
                    FishConfig.SELL_MULTIPLIERS[playerData().getSellMultiplier()] * playerData.getSellMultiplierCommonFish();
            playerData.setCommonFish(playerData().getCommonFish() - amount)
                    .setCash(playerData.getCash() + priceSoldFor).save();

            event.getMessage().replyEmbeds(new EmbedBuilder()
                    .setColor(Color.green)
                    .setTitle("You sold " + amount + " common fish for $" + df.format(priceSoldFor) + "!")
                    .addField("Common fish", "" + playerData.getCommonFish(), false)
                    .addField("Rare fish", "" + playerData.getRareFish(), false)
                    .addField("Exotic fish", "" + playerData.getExoticFish(), false)
                    .addField("Cash", "$" + playerData.getCash(), false)
                    .build()
            ).queue();
        }
    }

    public void sellRareFish(int amount) {
        if (playerData.getRareFish() < amount) {
            event.getMessage().reply("You don't have that many fish!").queue();
        } else {
            double priceSoldFor = amount * Double.parseDouble(getFishPrices().get(1)) *
                    playerData().getSellMultiplier() * playerData.getSellMultiplierRareFish();
            playerData.setRareFish(playerData().getRareFish() - amount)
                    .setCash(playerData.getCash() + priceSoldFor).save();

            event.getMessage().replyEmbeds(new EmbedBuilder()
                    .setColor(Color.green)
                    .setTitle("You sold " + amount + " rare fish for $" + df.format(priceSoldFor) + "!")
                    .addField("Common fish", "" + playerData.getCommonFish(), false)
                    .addField("Rare fish", "" + playerData.getRareFish(), false)
                    .addField("Exotic fish", "" + playerData.getExoticFish(), false)
                    .addField("Cash", "$" + playerData.getCash(), false)
                    .build()
            ).queue();
        }
    }

    public void sellExoticFish(int amount) {
        if (playerData.getExoticFish() < amount) {
            event.getMessage().reply("You don't have that many fish!").queue();
        } else {
            double priceSoldFor = amount * Double.parseDouble(getFishPrices().get(2)) *
                    playerData().getSellMultiplier() * playerData.getSellMultiplierExoticFish();
            playerData.setExoticFish(playerData().getExoticFish() - amount)
                    .setCash(playerData.getCash() + priceSoldFor).save();

            event.getMessage().replyEmbeds(new EmbedBuilder()
                    .setColor(Color.green)
                    .setTitle("You sold " + amount + " exotic fish for $" + df.format(priceSoldFor) + "!")
                    .addField("Common fish", "" + playerData.getCommonFish(), false)
                    .addField("Rare fish", "" + playerData.getRareFish(), false)
                    .addField("Exotic fish", "" + playerData.getExoticFish(), false)
                    .addField("Cash", "$" + playerData.getCash(), false)
                    .build()
            ).queue();
        }
    }

    public void sellAll() {
        if (playerData.getCommonFish() == 0 && playerData.getRareFish() == 0 && playerData.getExoticFish() == 0){
                event.getMessage().reply("You don't have any fish! Use !fish to get some.").queue();
        } else {
            double priceSoldForCommon = playerData.getCommonFish() * Double.parseDouble(getFishPrices().get(0))
                    * playerData.getSellMultiplierCommonFish();
            double priceSoldForRare   = playerData.getRareFish()   * Double.parseDouble(getFishPrices().get(1))
                    * playerData.getSellMultiplierRareFish();
            double priceSoldForExotic = playerData.getExoticFish() * Double.parseDouble(getFishPrices().get(2))
                    * playerData.getSellMultiplierExoticFish();
            double pricesSoldForTotal = priceSoldForCommon + priceSoldForRare + priceSoldForExotic
                    * playerData.getSellMultiplier();
            playerData.setCommonFish(0)
                    .setRareFish(0)
                    .setExoticFish(0)
                    .setCash(playerData.getCash() + pricesSoldForTotal).save();

            event.getMessage().replyEmbeds(new EmbedBuilder()
                    .setColor(Color.green)
                    .setTitle("You sold all your fish for $" + df.format(pricesSoldForTotal) + "!")
                    .addField("Common fish", "" + playerData.getCommonFish(), false)
                    .addField("Rare fish", "" + playerData.getRareFish(), false)
                    .addField("Exotic fish", "" + playerData.getExoticFish(), false)
                    .addField("Cash", "$" + playerData.getCash(), false)
                    .build()
            ).queue();
        }
    }

    public void upgradePerkFasterFishing(){
        int level = playerData.getPerkFasterFishing();
        if(level == 50){
            event.getMessage().reply("Congrats, you've already reached max level! No need to upgrade.").queue();
        } else if(level == 49) {
            long upgradePrice = FishConfig.FISH_TIMER_PRICES[level];
            double playerCash = playerData.getCash();
            if(playerCash < upgradePrice){
                event.getMessage().replyEmbeds(new EmbedBuilder()
                        .setTitle("Not enough cash!")
                        .setDescription("You need $" + (upgradePrice - playerCash) + " more dollars.")
                        .setColor(Color.red)
                        .addField("Upgrade cost", "$" + upgradePrice, false)
                        .addField("Your Cash", "$" + playerCash, false)
                        .build()).queue();
            } else {
                playerData.setPerkFasterFishing(level + 1).setCash(playerCash - upgradePrice).save();
                event.getMessage().replyEmbeds(new EmbedBuilder()
                        .setTitle("Faster Fishing perk upgraded to level " + (level + 1) + "/50")
                        .setDescription("You can now fish faster. Cast your rod every " + FishConfig.FISH_TIMER[level+1]/1000.0 + " seconds.")
                        .setColor(Color.green)
                        .addField("Upgrade cost", "$" + upgradePrice, false)
                        .addField("Your Cash", "$" + (playerCash - upgradePrice), false)
                        .addField("Next upgrade", FishConfig.FISH_TIMER[level + 2]/1000.0 + " seconds for $" +
                                FishConfig.FISH_TIMER_PRICES[level + 1], false)
                        .build()).queue();
            }
        } else {
            long upgradePrice = FishConfig.FISH_TIMER_PRICES[level];
            double playerCash = playerData.getCash();
            if(playerCash < upgradePrice){
                event.getMessage().replyEmbeds(new EmbedBuilder()
                        .setTitle("Not enough cash!")
                        .setDescription("You need $" + (upgradePrice - playerCash) + " more dollars.")
                        .setColor(Color.red)
                        .addField("Upgrade cost", "$" + upgradePrice, false)
                        .addField("Your Cash", "$" + playerCash, false)
                        .build()).queue();
            } else {
                playerData.setPerkFasterFishing(level + 1).setCash(playerCash - upgradePrice).save();
                event.getMessage().replyEmbeds(new EmbedBuilder()
                        .setTitle("Faster Fishing perk upgraded to level " + (level + 1) + "/50")
                        .setDescription("You can now fish faster. Cast your rod every " + FishConfig.FISH_TIMER[level+1]/1000.0 + " seconds.")
                        .setColor(Color.green)
                        .addField("Upgrade cost", "$" + upgradePrice, false)
                        .addField("Your Cash", "$" + (playerCash - upgradePrice), false)
                        .addField("Next upgrade", FishConfig.FISH_TIMER[level + 2]/1000.0 + " seconds for $" +
                                FishConfig.FISH_TIMER_PRICES[level + 1], false)
                        .build()).queue();
            }
        }
    }

    public void upgradeSellRate(){
        int level = playerData.getSellMultiplier() + 1;
        int upgradePrice = FishConfig.SELL_MULTIPLIER_PRICES[level];
        double playerCash = playerData.getCash();
        if(playerData.getSellMultiplier() == 20) {
            event.getMessage().reply("Congrats, you've already reached max level! No need to upgrade.").queue();
        } else if(playerData.getSellMultiplier() == 19) {
            if(playerCash < upgradePrice) {
                event.getMessage().replyEmbeds(new EmbedBuilder()
                        .setTitle("Not enough cash!")
                        .setDescription("You need $" + (upgradePrice - playerCash) + " more dollars.")
                        .setColor(Color.red)
                        .addField("Upgrade cost", "$" + upgradePrice, false)
                        .addField("Your Cash", "$" + playerCash, false)
                        .build()).queue();
            } else {
                playerData.setSellMultiplier(20).setCash(playerCash - upgradePrice).save();
                event.getMessage().replyEmbeds(new EmbedBuilder()
                        .setTitle("Sell multiplier upgraded to level 20/20!!! Congrats!!")
                        .setDescription("Your sell multiplier is now " + FishConfig.SELL_MULTIPLIERS[level] + ". This will not go any higher.")
                        .setColor(Color.green)
                        .addField("Upgrade cost", "$" + upgradePrice, false)
                        .addField("Your Cash", "$" + (playerCash - upgradePrice), false)
                        .setFooter("You've reached max level on this perk. You cannot upgrade it anymore.").build()).queue();
            }
        } else {
            if(playerCash < upgradePrice) {
                event.getMessage().replyEmbeds(new EmbedBuilder()
                        .setTitle("Not enough cash!")
                        .setDescription("You need $" + (upgradePrice - playerCash) + " more dollars.")
                        .setColor(Color.red)
                        .addField("Upgrade cost", "$" + upgradePrice, false)
                        .addField("Your Cash", "$" + playerCash, false)
                        .build()).queue();
            } else {
                playerData.setSellMultiplier(level).setCash(playerCash - upgradePrice).save();
                event.getMessage().replyEmbeds(new EmbedBuilder()
                        .setTitle("Sell multiplier upgraded to level " + level + "/20")
                        .setDescription("Your sell multiplier is now " + FishConfig.SELL_MULTIPLIERS[level])
                        .setColor(Color.green)
                        .addField("Upgrade cost", "$" + upgradePrice, false)
                        .addField("Your Cash", "$" + (playerCash - upgradePrice), false)
                        .addField("Next upgrade", FishConfig.SELL_MULTIPLIERS[level + 1] + " for $" + FishConfig.SELL_MULTIPLIER_PRICES[level + 1], false)
                        .build()).queue();
            }
        }
    }



    List<String> getFishPrices(){
        try {
           return Files.readAllLines(Path.of("data/fish/prices.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
