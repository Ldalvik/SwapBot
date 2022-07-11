package root.ogbot.fish;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FishListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if(args[0].equalsIgnoreCase("!wallet")){
            if(checkNewPlayer(event)) return;
            new FishGame(new FishUserData(event.getAuthor().getId()), event)
                    .getWallet();
        }
        if(args[0].equalsIgnoreCase("!fish")){
            if(checkNewPlayer(event)) return;
            new FishGame(new FishUserData(event.getAuthor().getId()), event)
                    .goFish();
        }

        if(args[0].equalsIgnoreCase("!sellcommon")){
            if(checkNewPlayer(event)) return;
            new FishGame(new FishUserData(event.getAuthor().getId()), event)
                    .sellCommonFish(Integer.parseInt(args[1]));
        }

        if(args[0].equalsIgnoreCase("!sellrare")){
            if(checkNewPlayer(event)) return;
            new FishGame(new FishUserData(event.getAuthor().getId()), event)
                    .sellRareFish(Integer.parseInt(args[1]));

        }

        if(args[0].equalsIgnoreCase("!sellexotic")){
            if(checkNewPlayer(event)) return;
            new FishGame(new FishUserData(event.getAuthor().getId()), event)
                    .sellExoticFish(Integer.parseInt(args[1]));

        }

        if(args[0].equalsIgnoreCase("!sellall")){
            if(checkNewPlayer(event)) return;
            new FishGame(new FishUserData(event.getAuthor().getId()), event)
                    .sellAll();

        }

        if(args[0].equalsIgnoreCase("!upgrade")) {
            if(!checkNewPlayer(event)) {
                FishGame fish = new FishGame(new FishUserData(event.getAuthor().getId()), event);
                if (args[1].equalsIgnoreCase("sellrate")) {
                    switch (args[2]) {
                        //case "common" -> fish.upgradeSellRateCommon();
                        //case "rare" -> fish.upgradeSellRateRare();
                        //case "exotic" -> fish.upgradeSellRateExotic();
                        case "all" -> fish.upgradeSellRate();
                    }
                }
                if (args[1].equalsIgnoreCase("maxfish")) {
                    switch (args[2]) {
                        //case "common" -> fish.upgradeMaxFishCommon();
                        //case "rare" -> fish.upgradeMaxFishRare();
                        //case "exotic" -> fish.upgradeMaxFishExotic();
                    }
                }
                if (args[1].equalsIgnoreCase("bonusfish")) {
                    switch (args[2]) {
                        //case "common" -> fish.upgradeMinFishCommon();
                        //case "rare" -> fish.upgradeMinFishRare();
                        //case "exotic" -> fish.upgradeMinFishExotic();
                    }
                }
                if (args[1].equalsIgnoreCase("maxtreasure")) {
                    //fish.upgradeMaxTreasure();
                }
                if(args[1].equalsIgnoreCase("fasterfishing")){
                    fish.upgradePerkFasterFishing();
                }
            }
        }
    }

    private boolean checkNewPlayer(MessageReceivedEvent event){
        boolean isNew = false;
            File file = new File("data/fish/users/" + event.getAuthor().getId());
            if(!file.exists()) {
                try {
                    file.createNewFile();
                    FileWriter myWriter = new FileWriter(file);
                    myWriter.write("0,0,0,0,10,0,5,0,3,0,1000,0,0,0,0,0,0");
                    myWriter.close();
                    event.getMessage().replyEmbeds(new EmbedBuilder()
                            .setColor(Color.cyan)
                            .setTitle("Welcome new player!")
                            .setDescription("Welcome " + event.getAuthor().getName() + "! Typing /fish will cast your rod for a chance to catch " +
                            "one of 4 items. Common, rare, and exotic fish, as well as treasure that contains cash.\nTo sell your fish, " +
                                    "use /sellcommon [amount|all], /sellrare [amount|all], /sellexotic [amount|all], or /sellall to sell " +
                                    "everything.").build()).queue();
                    isNew = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Player is new: " + isNew);
            return isNew;
    }
}
