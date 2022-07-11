package swap.bot.root;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import root.ogbot.utils.ReadData;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static swap.bot.root.Config.*;

public class ReadyListener extends ListenerAdapter {
    long start;

    public ReadyListener(long start) {
        this.start = start;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        int loadTime = (int) (System.currentTimeMillis() - start);
        JDA jda = event.getJDA();

        //event.getJDA().getGuilds().forEach(guild -> System.out.println(guild.getName()));
        //event.getJDA().getGuildById("969809178401050655").getTextChannelById("976361208670224414")
        //        .sendMessageEmbeds(StockMarketListener.stockPrice().build()).queue();
        //Bot channel LuAu send ONLINE status message
        //Queues.LUAU_Ready(jda, loadTime, fastest, slowest);


        //Send online message to status channel, delete after 30 seconds
        jda.getGuildById(Guild_SWAPBOT).getTextChannelById(Guild_SWAPBOT_Channel_STATUS)
                .sendMessage("I came online in " + loadTime + " ms!\n" +
                        "(This message will be deleted in 30 seconds)")
                .delay(30, TimeUnit.SECONDS)
                .flatMap(Message::delete).queue();
    }
}

