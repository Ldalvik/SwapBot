package swap.bot.root.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;

public class HelpCommand {

    static EmbedBuilder allCommands = new EmbedBuilder()
            .setTitle("Help menu / All commands", "https://ldalvik.gitub.io/SwapBot/")
            .setAuthor("Swap Bot (v0.1.0)")
            .setColor(Color.cyan)
            .setDescription("""
                    SwapBot was created to solve an issue that we've struggled with since Forza Horizon 2: Ugly parts on a locked tune. Now you can change that.\s
                    This bot can change rims, front/rear bumpers, side-skirts, hood, rear wing, and body-kits on any tune you want.
                    Click on one of the buttons below for more help.""")
            .addField("`/help`", "Shows this help menu.", false)
            .addField("`/rims`", "Get list of all rims and RIM_IDS", false)
            .addField("`/ping`", "Check the bots response time.", false)
            .addField("`/website`", "SwapBot website", false)
            .addField("`!uptime`", "Shows how long the bot has been online. (Cycles every 24 hours)", false)
            .addField("`!info [tuneFile]`", "Gets rims and part indexes for a tune file.", false)
            .addField("`!rims [RIM_ID] [tuneFile*]`", "Swap rims on a tune. Find your RIM_ID with `/rims`", false)
            .addField("`!hood [partIndex] [tuneFile*]`", "Swap the hood on a tune.", false)
            .addField("`!frontbumper [partIndex] [tuneFile*]`", "Swap the front bumper on a tune.", false)
            .addField("`!rearbumper [partIndex] [tuneFile*]`", "Swap the rear bumper on a tune.", false)
            .addField("`!wing [partIndex] [tuneFile*]`", "Swap the rear wing on a tune.", false)
            .addField("`!skirt [partIndex] [tuneFile*]`", "Swap the side-skirts on a tune.", false)
            .addField("`!bodykit [partIndex] [tuneFile*]`", "Swap the body-kit on a tune.", false)
            .setFooter("*Remember to ATTACH the tune file to the command before sending.");
    static EmbedBuilder swapRims = new EmbedBuilder()
            .setTitle("Swapping rims")
            .setColor(Color.cyan)
            .setDescription("To get the ID of the rim you want to swap, type `/rims`. Use the buttons to find your rim by letter. Once you find " +
                    "your rim, remember the ID as you'll need it for the command. For example, to switch the rims to " +
                    "Work XD9's, you would attach the tune file to the command `!rims WOR_XD9` and send the message. " +
                    "The bot will reply with the tune file that you must REPLACE the old tune file with. If the name isn't the same, " +
                    "(trailing (1) or _1) make sure to rename it.");

    static EmbedBuilder swapAppearance = new EmbedBuilder()
            .setTitle("Swapping Aero and Appearance parts")
            .setColor(Color.cyan)
            .setDescription("To get the index of the part you want on the car, use the default (stock) part as 0. If there " +
                    "is a 3x3 grid, the largest index you could put is 8. Note that the tune will say \"incompatible\" if you go out of bounds. " +
                    "This also applies to swapping the body-kit. if there is only one body-kit choice, the command would be `!bodykit 1`")
            .addField("EXAMPLE: Command to change the rear wing to default (stock)", "`!wing 0`", false)
            .addField("EXAMPLE: Command to change the front bumper to second choice", "`!frontbumper 1`", false)
            .setFooter("Visit the website by typing /website", null);

    static EmbedBuilder tuneFiles = new EmbedBuilder()
            .setTitle("Finding your tune file")
            .setColor(Color.CYAN)
            .setDescription("To find the right file, make sure it's the most recently downloaded tune (378 bytes). " +
                    "If not, download and save/install the tune you want to change. \nTo find the file, follow these instructions:")

            .addField("Microsoft version", "`C:/Users/[USER]/AppData/Local/Packages/Microsoft.624F8B84B80_8wekyb3d8bbwe/SystemAppData/wgs/`", false)
            .addField("Which file is it?",
                    "*(You may have 3+ folders (ignore the `t` folder) in your wgs directory if you have multiple accounts signed in.)*\n" +
                            "If you don't know which XUID is yours, it should be the most recently modified folder (after you download a tune). " +
                            "Once you are in this folder, you should see a bunch of hashed folder names and a container file. Open up the most " +
                            "recently created/modified folder in here as well. You should see a few 1kb files and another container file. " +
                            "Hover over each file, and find the one that is 378 bytes."
                    , false)
            .addBlankField(false)
            .addField("Steam Version", "`C:/Program Files (x86)/Steam/userdata/[STEAMID]/155136/remote/[XUID]/`", false)
            .addField("Which File is it?",
                    "Steam is a lot simpler. Download your tune and open up the most recently modified directory in the remote folder. " +
                            "You should see names along the lines of \n`Tuning_####[DATE]####.Data` as well as ones with `.header` and thumbnails. " +
                            "You want the one that ends in `.Data`. Make sure it's the tune you downloaded by setting it to recently created/modified, " +
                            "and check that the file is 378 bytes.", false)
            .setFooter("Visit the website by typing /website", null);

}


