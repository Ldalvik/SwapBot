package swap.bot.root.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equalsIgnoreCase("ping")) {
            long time = System.currentTimeMillis();
            event.getChannel().sendMessageEmbeds(new EmbedBuilder()
                            .setColor(Color.cyan)
                            .setTitle("Ping!")
                            .addField("Response time", "Pinging...", false).build())
                    .queue(response -> {
                        long responseTime = System.currentTimeMillis() - time;
                        response.editMessageEmbeds(new EmbedBuilder()
                                        .setColor(Color.cyan)
                                        .setTitle("Pong!")
                                        .addField("Response time", responseTime + "ms", false).build())
                                .queue();
                    });
        }

        if(event.getName().equalsIgnoreCase("help")) {
            event.replyEmbeds(HelpCommand.allCommands.build()).addActionRow(
                    net.dv8tion.jda.api.interactions.components.buttons.Button.primary("tuneFiles", "Find tune files"),
                    net.dv8tion.jda.api.interactions.components.buttons.Button.primary("swapAppearance", "Swap aero/appearance"),
                    Button.primary("swapRims", "Swap rims")
            ).queue();
        }

        if (event.getName().equalsIgnoreCase("website")) {
            event.reply("https://ldalvik.github.io/SwapBot/").queue();
        }

        if(event.getName().equalsIgnoreCase("rims")) {
            event.reply("Rim IDS").addActionRow(
                    Button.primary("pageOne", "#-F"),
                    Button.primary("pageTwo", "G-K"),
                    Button.primary("pageThree", "L-P"),
                    Button.primary("pageFour", "Q-U"),
                    Button.primary("pageFive", "V-Z")
            ).queue();
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        switch (event.getComponentId()){
            case "tuneFiles" -> event.replyEmbeds(HelpCommand.tuneFiles.build()).queue();
            case "swapAppearance" -> event.replyEmbeds(HelpCommand.swapAppearance.build()).queue();
            case "swapRims" -> event.replyEmbeds(HelpCommand.swapRims.build()).queue();
            case "pageOne" -> event.editMessageEmbeds(RimsCommand.getFirstPage().build()).queue();
            case "pageTwo" -> event.editMessageEmbeds(RimsCommand.getSecondPage().build()).queue();
            case "pageThree" -> event.editMessageEmbeds(RimsCommand.getThirdPage().build()).queue();
            case "pageFour" -> event.editMessageEmbeds(RimsCommand.getFourthPage().build()).queue();
            case "pageFive" -> event.editMessageEmbeds(RimsCommand.getFifthPage().build()).queue();

            default -> event.replyEmbeds(new EmbedBuilder().setTitle("UNKNOWN ID")
                    .setDescription("Unknown ID found: " + event.getComponentId()).build()).queue();
        }
    }
}
