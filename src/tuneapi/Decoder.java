package swap.bot.root.tuneapi;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import swap.bot.root.tuneapi.Upgrades.UpgradeApi;
import swap.bot.root.tuneapi.Utils.Data;
import swap.bot.root.tuneapi.Utils.Utils;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;

public class Decoder {
    static UpgradeApi upgrades;
    static MetadataApi metadata;
    static MessageReceivedEvent event;
    static File file;

    public static void run(MessageReceivedEvent e, File f) throws IOException {
        event = e;
        file = f;
        byte[] fileBytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        if(fileBytes.length != 378){
            event.getMessage().reply("ERROR").setEmbeds(errorWrongFileSize(fileBytes.length).build())
                    .queue();
            return;
        }
        ByteBuffer buf = ByteBuffer.wrap(fileBytes);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        upgrades   = new UpgradeApi(buf);
        metadata   = new MetadataApi(buf);
    }

    public static void changeRims(String rimId) {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("RIM SWAP SUCCESSFUL", null)
                .setColor(Color.GREEN);

        String carName = metadata.getCarName();

        if(carName.startsWith("Ordinal:")){
            embed.setAuthor(carName)
                    .setColor(Color.yellow)
                    .setTitle("RIM SWAP SUCCESSFUL (WARNING)", null);
            carName = "Car not in database. This is usually because " +
                    "of a newer car I haven't added. Your tune file is still usable.";
        }

        embed.addField("Car", carName, false)
                .addField("Original rims", upgrades.getTiresAndRims().getRims(), false)
                .addField("New rims", rimId, false)
                .setFooter("If you need help, message root.#6923 or ask for help in the discord.", null);

        try {
            byte[] array = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
            int id = 0;
            for(String line : Data.RIMS_ID)
                if (line.toLowerCase().split(",")[1].equals(rimId.toLowerCase()))
                    id = Integer.parseInt(line.split(",")[0]);

            /*
             * DO NOT ASK FOR THIS CODE. YOU WILL 
             * NOT GET IT.
             */

            FileOutputStream f = new FileOutputStream(file.getAbsoluteFile());
            f.write(array);
            f.close();
        } catch (IOException e) {
            embed = new EmbedBuilder()
                    .setTitle("RIM SWAP FAILED", null)
                    .setColor(Color.RED)
                    .setDescription("Rim swap failed. Please send the below timestamp to root.#6923.")
                    .addBlankField(false)
                    .addField("Rims selected", rimId, false)
                    .addBlankField(false)
                    .addField("Exception", "```" + e.getMessage() + "```", false)
                    .setTimestamp(Instant.now())
                    .setAuthor("Swap Bot");
            e.printStackTrace();
        }
        event.getMessage().replyEmbeds(embed.build())
                .addFile(new File(file.getAbsolutePath())).queue();
    }

    public static void changeFrontBumper(String bumperId){
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("FRONT BUMPER SWAP SUCCESSFUL", null)
                .setColor(Color.GREEN);
        String carName = metadata.getCarName();

        if(carName.startsWith("Ordinal:")){
            embed.setAuthor(carName)
                    .setColor(Color.yellow)
                    .setTitle("BUMPER SWAP SUCCESSFUL (WARNING)", null);
            carName = "Car not in database. This is usually because " +
                    "of a newer car I haven't added. Your tune file is still usable.";
        }

        embed.addField("Car", carName, false)
                .addField("Original bumper", upgrades.getAppearance().getBumperFront(), false)
                .addField("New bumper", bumperId, false)
                .setFooter("If you need help, message root.#6923 or ask for help in the discord.", null);

        try {
            byte[] array = Files.readAllBytes(Paths.get(file.getAbsolutePath()));

             /*
             * DO NOT ASK FOR THIS CODE. YOU WILL 
             * NOT GET IT.
             */

            FileOutputStream f = new FileOutputStream(file.getAbsoluteFile());
            f.write(array);
            f.close();
        } catch (IOException e) {
            embed = new EmbedBuilder()
                    .setTitle("BUMPER SWAP FAILED", null)
                    .setColor(Color.RED)
                    .setDescription("Bumper swap failed. Please send the below timestamp to root.#6923.")
                    .addBlankField(false)
                    .addField("Bumper selected", bumperId, false)
                    .addBlankField(false)
                    .addField("Exception", "```" + e.getMessage() + "```", false)
                    .setTimestamp(Instant.now())
                    .setAuthor("Swap Bot");
            e.printStackTrace();
        }
        event.getMessage().replyEmbeds(embed.build())
                .addFile(new File(file.getAbsolutePath())).queue();
    }

    public static void changeRearBumper(String bumperId){
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("REAR BUMPER SWAP SUCCESSFUL", null)
                .setColor(Color.GREEN);
        String carName = metadata.getCarName();

        if(carName.startsWith("Ordinal:")){
            embed.setAuthor(carName)
                    .setColor(Color.yellow)
                    .setTitle("BUMPER SWAP SUCCESSFUL (WARNING)", null);
            carName = "Car not in database. This is usually because " +
                    "of a newer car I haven't added. Your tune file is still usable.";
        }

        embed.addField("Car", carName, false)
                .addField("Original bumper", upgrades.getAppearance().getBumperRear(), false)
                .addField("New bumper", bumperId, false)
                .setFooter("If you need help, message root.#6923 or ask for help in the discord.", null);

        try {
            byte[] array = Files.readAllBytes(Paths.get(file.getAbsolutePath()));

            /*
             * DO NOT ASK FOR THIS CODE. YOU WILL 
             * NOT GET IT.
             */

            FileOutputStream f = new FileOutputStream(file.getAbsoluteFile());
            f.write(array);
            f.close();
        } catch (IOException e) {
            embed = new EmbedBuilder()
                    .setTitle("BUMPER SWAP FAILED", null)
                    .setColor(Color.RED)
                    .setDescription("Bumper swap failed. Please send the below timestamp to root.#6923.")
                    .addBlankField(false)
                    .addField("Bumper selected", bumperId, false)
                    .addBlankField(false)
                    .addField("Exception", "```" + e.getMessage() + "```", false)
                    .setTimestamp(Instant.now())
                    .setAuthor("Swap Bot");
            e.printStackTrace();
        }
        event.getMessage().replyEmbeds(embed.build())
                .addFile(new File(file.getAbsolutePath())).queue();
    }

    public static void changeRearWing(String wingId){
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("REAR WING SWAP SUCCESSFUL", null)
                .setColor(Color.GREEN);
        String carName = metadata.getCarName();

        if(carName.startsWith("Ordinal:")){
            embed.setAuthor(carName)
                    .setColor(Color.yellow)
                    .setTitle("REAR WING SWAP SUCCESSFUL (WARNING)", null);
            carName = "Car not in database. This is usually because " +
                    "of a newer car I haven't added. Your tune file is still usable.";
        }

        embed.addField("Car", carName, false)
                .addField("Original wing", upgrades.getAppearance().getWingRear(), false)
                .addField("New wing", wingId, false)
                .setFooter("If you need help, message root.#6923 or ask for help in the discord.", null);

        try {
            byte[] array = Files.readAllBytes(Paths.get(file.getAbsolutePath()));

            /*
             * DO NOT ASK FOR THIS CODE. YOU WILL 
             * NOT GET IT.
             */

            FileOutputStream f = new FileOutputStream(file.getAbsoluteFile());
            f.write(array);
            f.close();
        } catch (IOException e) {
            embed = new EmbedBuilder()
                    .setTitle("REAR WING SWAP FAILED", null)
                    .setColor(Color.RED)
                    .setDescription("Wing swap failed. Please send the below timestamp to root.#6923.")
                    .addBlankField(false)
                    .addField("Wing selected", wingId, false)
                    .addBlankField(false)
                    .addField("Exception", "```" + e.getMessage() + "```", false)
                    .setTimestamp(Instant.now())
                    .setAuthor("Swap Bot");
            e.printStackTrace();
        }
        event.getMessage().replyEmbeds(embed.build())
                .addFile(new File(file.getAbsolutePath())).queue();
    }

    public static void changeSkirt(String skirtId){
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("SIDESKIRT SWAP SUCCESSFUL", null)
                .setColor(Color.GREEN);
        String carName = metadata.getCarName();

        if(carName.startsWith("Ordinal:")){
            embed.setAuthor(carName)
                    .setColor(Color.yellow)
                    .setTitle("SIDESKIRT SWAP SUCCESSFUL (WARNING)", null);
            carName = "Car not in database. This is usually because " +
                    "of a newer car I haven't added. Your tune file is still usable.";
        }

        embed.addField("Car", carName, false)
                .addField("Original skirt", upgrades.getAppearance().getSideSkirts(), false)
                .addField("New skirt", skirtId, false)
                .setFooter("If you need help, message root.#6923 or ask for help in the discord.", null);

        try {
            byte[] array = Files.readAllBytes(Paths.get(file.getAbsolutePath()));

            /*
             * DO NOT ASK FOR THIS CODE. YOU WILL 
             * NOT GET IT.
             */

            FileOutputStream f = new FileOutputStream(file.getAbsoluteFile());
            f.write(array);
            f.close();
        } catch (IOException e) {
            embed = new EmbedBuilder()
                    .setTitle("SIDESKIRT SWAP FAILED", null)
                    .setColor(Color.RED)
                    .setDescription("Skirt swap failed. Please send the below timestamp to root.#6923.")
                    .addBlankField(false)
                    .addField("Skirt selected", skirtId, false)
                    .addBlankField(false)
                    .addField("Exception", "```" + e.getMessage() + "```", false)
                    .setTimestamp(Instant.now())
                    .setAuthor("Swap Bot");
            e.printStackTrace();
        }
        event.getMessage().replyEmbeds(embed.build())
                .addFile(new File(file.getAbsolutePath())).queue();
    }

    public static void changeBodyKit(String bodykitId){
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("BODYKIT SWAP SUCCESSFUL", null)
                .setColor(Color.GREEN);
        String carName = metadata.getCarName();

        if(carName.startsWith("Ordinal:")){
            embed.setAuthor(carName)
                    .setColor(Color.yellow)
                    .setTitle("BODYKIT SWAP SUCCESSFUL (WARNING)", null);
            carName = "Car not in database. This is usually because " +
                    "of a newer car I haven't added. Your tune file is still usable.";
        }

        embed.addField("Car", carName, false)
                .addField("Original bodykit", upgrades.getAppearance().getCarBody(), false)
                .addField("New bodykit", bodykitId, false)
                .setFooter("If you need help, message root.#6923 or ask for help in the discord.", null);

        try {
            byte[] array = Files.readAllBytes(Paths.get(file.getAbsolutePath()));

           /*
             * DO NOT ASK FOR THIS CODE. YOU WILL 
             * NOT GET IT.
             */

            FileOutputStream f = new FileOutputStream(file.getAbsoluteFile());
            f.write(array);
            f.close();
        } catch (IOException e) {
            embed = new EmbedBuilder()
                    .setTitle("BODYKIT SWAP FAILED", null)
                    .setColor(Color.RED)
                    .setDescription("Bodykit swap failed. Please send the below timestamp to root.#6923.")
                    .addBlankField(false)
                    .addField("Bodykit selected", bodykitId, false)
                    .addBlankField(false)
                    .addField("Exception", "```" + e.getMessage() + "```", false)
                    .setTimestamp(Instant.now())
                    .setAuthor("Swap Bot");
            e.printStackTrace();
        }
        event.getMessage().replyEmbeds(embed.build())
                .addFile(new File(file.getAbsolutePath())).queue();
    }

    public static void changeHood(String hoodId){
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("HOOD SWAP SUCCESSFUL", null)
                .setColor(Color.GREEN);
        String carName = metadata.getCarName();

        if(carName.startsWith("Ordinal:")){
            embed.setAuthor(carName)
                    .setColor(Color.yellow)
                    .setTitle("HOOD SWAP SUCCESSFUL (WARNING)", null);
            carName = "Car not in database. This is usually because " +
                    "of a newer car I haven't added. Your tune file is still usable.";
        }

        embed.addField("Car", carName, false)
                .addField("Original hood", String.valueOf(upgrades.getAppearance().getHood()), false)
                .addField("New hood", hoodId, false)
                .setFooter("If you need help, message root.#6923 or ask for help in the discord.", null);

        try {
            byte[] array = Files.readAllBytes(Paths.get(file.getAbsolutePath()));

             /*
             * DO NOT ASK FOR THIS CODE. YOU WILL 
             * NOT GET IT.
             */

            FileOutputStream f = new FileOutputStream(file.getAbsoluteFile());
            f.write(array);
            f.close();
        } catch (IOException e) {
            embed = new EmbedBuilder()
                    .setTitle("HOOD SWAP FAILED", null)
                    .setColor(Color.RED)
                    .setDescription("Hood swap failed. Please send the below timestamp to root.#6923.")
                    .addBlankField(false)
                    .addField("Hood selected", hoodId, false)
                    .addBlankField(false)
                    .addField("Exception", "```" + e.getMessage() + "```", false)
                    .setTimestamp(Instant.now())
                    .setAuthor("Swap Bot");
            e.printStackTrace();
        }
        event.getMessage().replyEmbeds(embed.build())
                .addFile(new File(file.getAbsolutePath())).queue();
    }


    public static void getInfo(){
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("CAR INFO", null)
                .setColor(Color.GREEN);
        String carName = metadata.getCarName();

        if(carName.startsWith("Ordinal:")){
            embed.setAuthor(carName)
                    .setColor(Color.yellow)
                    .setTitle("CAR INFO (WARNING)", null);
            carName = "Car not in database. This is usually because " +
                    "of a newer car I haven't added. Your tune file is still usable.";
        }

        embed.addField("Car", carName, false)
                .addField("Front bumper", upgrades.getAppearance().getBumperFront(), false)
                .addField("Rear bumper", upgrades.getAppearance().getBumperRear(), false)
                .addField("Side-skirts", upgrades.getAppearance().getSideSkirts(), false)
                .addField("Rear wing", upgrades.getAppearance().getWingRear(), false)
                .addField("Rims", upgrades.getTiresAndRims().getRims(), false)
                .addField("Body-kit", upgrades.getAppearance().getCarBody(), false)
                .setFooter("Ordinal: " + metadata.getOrdinal(), null);
        event.getMessage().replyEmbeds(embed.build()).queue();
    }

    public static EmbedBuilder errorWrongFileSize(int fileSize) {
        return new EmbedBuilder()
                .setTitle("RIM SWAP FAILED", null)
                .setColor(Color.RED)
                .setDescription("""
                        File size is incorrect, please make sure it is a tune file. You can find a tutorial
                        on the website or by using the `!help swap` command.
                        """)
                .addField("File size:", "" + fileSize, false)
                .setFooter("If you need help, message root.#6923 or ask for help in the discord.", null);
    }
}
