package swap.bot.root;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import swap.bot.root.commands.*;
import swap.bot.root.stockmarket.StockMarketListener;

public class SwapBot {
    public static void main(String[] arguments) throws Exception {
        //Start JDA with BOT_TOKEN set in Heroku CONFIG VARS
        JDABuilder api = JDABuilder.createDefault(System.getenv("BOT_TOKEN"));
        api.setActivity(Activity.watching("for commands like /help..."));

        api.addEventListeners(
                new ReadyListener(System.currentTimeMillis()), //Tasks to do when bot is online (onReady)
                new SwapListener(),
                new CommandListener(),
                new UptimeListener(System.currentTimeMillis()),
                new StockMarketListener(),
        ).build().awaitReady(); //Wait for bot to be ready to trigger listeners (like onReady)

        System.out.println("<console> BOT STARTED");
    }
}

//heroku deploy:jar SwapBot-1.0-all.jar --app app-name
