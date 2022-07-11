package swap.bot.root.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UptimeListener extends ListenerAdapter {
    long startTime;

    public UptimeListener(long startTime){
        this.startTime = startTime;
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getMessage().getContentRaw().equalsIgnoreCase("!uptime")) {
            event.getChannel().sendMessageEmbeds(
                    new EmbedBuilder()
                            .setTitle("Bot Uptime")
                            .setColor(Color.cyan)
                            .setDescription("Bot should cycle every day to reset cache, so it should never exceed 24 hrs")
                            .addField("Uptime", getUptime(System.currentTimeMillis() - startTime), false)
                            .build()).queue();
        }
    }

public String getUptime(long time){
    long days = TimeUnit.MILLISECONDS.toDays(time);
    time -= TimeUnit.DAYS.toMillis(days);
    long hours = TimeUnit.MILLISECONDS.toHours(time);
    time -= TimeUnit.HOURS.toMillis(hours);
    long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
    time -= TimeUnit.MINUTES.toMillis(minutes);
    long seconds = TimeUnit.MILLISECONDS.toSeconds(time);

    return("Days (" + days + ")\n" +
            "Hours (" + hours + ")\n" +
            "Minutes (" + minutes + ")\n" +
            "Seconds (" + seconds + ")");
    }
}

