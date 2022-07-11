package swap.bot.root.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import swap.bot.root.tuneapi.Decoder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SwapListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        if(event.getAuthor().isBot()) return;

        if(event.getMessage().getContentRaw().startsWith("!rims")) {
            //Grab parameters (if any) and splits them
            String[] args = event.getMessage().getContentRaw().split("\\s+");
            if (args.length == 1) { //If command has no parameters
                event.getMessage()
                        .reply("You need to supply a `RIM_ID` to this command.")
                        .queue();
            } else {
                List<Message.Attachment> attachments = event.getMessage().getAttachments();
                if (attachments.isEmpty()) {
                    event.getMessage()
                            .reply("You need to supply a tune file to this command.")
                            .queue();
                    return;
                }

                //Download file to directory. No deletion needed when running on Heroku
                //as storage is not persistent (Cycles every 24hrs)
                CompletableFuture<File> future = attachments.get(0).downloadToFile("data/tunes/" +
                        attachments.get(0).getFileName());
                future.exceptionally(error -> {
                    error.printStackTrace();
                    return null;
                });
                try {
                    Decoder.run(event, future.get());
                    Decoder.changeRims(args[1]);
                } catch (InterruptedException | ExecutionException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if(event.getMessage().getContentRaw().startsWith("!frontbumper")) {
            //Grab parameters (if any) and splits them
            String[] args = event.getMessage().getContentRaw().split("\\s+");
            if (args.length == 1) { //If command has no parameters
                event.getMessage()
                        .reply("You need to supply an index to this command.")
                        .queue();
            } else {
                List<Message.Attachment> attachments = event.getMessage().getAttachments();
                if (attachments.isEmpty()) {
                    event.getMessage()
                            .reply("You need to supply a tune file to this command.")
                            .queue();
                    return;
                }

                //Download file to directory. No deletion needed when running on Heroku
                //as storage is not persistent (Cycles every 24hrs)
                CompletableFuture<File> future = attachments.get(0).downloadToFile("data/tunes/" +
                        attachments.get(0).getFileName());
                future.exceptionally(error -> {
                    error.printStackTrace();
                    return null;
                });
                try {
                    Decoder.run(event, future.get());
                    Decoder.changeFrontBumper(args[1]);
                } catch (InterruptedException | ExecutionException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if(event.getMessage().getContentRaw().startsWith("!rearbumper")) {
            //Grab parameters (if any) and splits them
            String[] args = event.getMessage().getContentRaw().split("\\s+");
            if (args.length == 1) { //If command has no parameters
                event.getMessage()
                        .reply("You need to supply an index to this command. Use `!help` to get help.")
                        .queue();
            } else {
                List<Message.Attachment> attachments = event.getMessage().getAttachments();
                if (attachments.isEmpty()) {
                    event.getMessage()
                            .reply("You need to supply a tune file to this command.")
                            .queue();
                    return;
                }

                //Download file to directory. No deletion needed when running on Heroku
                //as storage is not persistent (Cycles every 24hrs)
                CompletableFuture<File> future = attachments.get(0).downloadToFile("data/tunes/" +
                        attachments.get(0).getFileName());
                future.exceptionally(error -> {
                    error.printStackTrace();
                    return null;
                });
                try {
                    Decoder.run(event, future.get());
                    Decoder.changeRearBumper(args[1]);
                } catch (InterruptedException | ExecutionException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if(event.getMessage().getContentRaw().startsWith("!skirt")) {
            //Grab parameters (if any) and splits them
            String[] args = event.getMessage().getContentRaw().split("\\s+");
            if (args.length == 1) { //If command has no parameters
                event.getMessage()
                        .reply("You need to supply an index to this command.")
                        .queue();
            } else {
                List<Message.Attachment> attachments = event.getMessage().getAttachments();
                if (attachments.isEmpty()) {
                    event.getMessage()
                            .reply("You need to supply a tune file to this command.")
                            .queue();
                    return;
                }

                //Download file to directory. No deletion needed when running on Heroku
                //as storage is not persistent (Cycles every 24hrs)
                CompletableFuture<File> future = attachments.get(0).downloadToFile("data/tunes/" +
                        attachments.get(0).getFileName());
                future.exceptionally(error -> {
                    error.printStackTrace();
                    return null;
                });
                try {
                    Decoder.run(event, future.get());
                    Decoder.changeSkirt(args[1]);
                } catch (InterruptedException | ExecutionException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if(event.getMessage().getContentRaw().startsWith("!hood")) {
            //Grab parameters (if any) and splits them
            String[] args = event.getMessage().getContentRaw().split("\\s+");
            if (args.length == 1) { //If command has no parameters
                event.getMessage()
                        .reply("You need to supply an index to this command.")
                        .queue();
            } else {
                List<Message.Attachment> attachments = event.getMessage().getAttachments();
                if (attachments.isEmpty()) {
                    event.getMessage()
                            .reply("You need to supply a tune file to this command.")
                            .queue();
                    return;
                }

                //Download file to directory. No deletion needed when running on Heroku
                //as storage is not persistent (Cycles every 24hrs)
                CompletableFuture<File> future = attachments.get(0).downloadToFile("data/tunes/" +
                        attachments.get(0).getFileName());
                future.exceptionally(error -> {
                    error.printStackTrace();
                    return null;
                });
                try {
                    Decoder.run(event, future.get());
                    Decoder.changeHood(args[1]);
                } catch (InterruptedException | ExecutionException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if(event.getMessage().getContentRaw().startsWith("!wing")) {
            //Grab parameters (if any) and splits them
            String[] args = event.getMessage().getContentRaw().split("\\s+");
            if (args.length == 1) { //If command has no parameters
                event.getMessage()
                        .reply("You need to supply an index to this command. Use `!help` to get help.")
                        .queue();
            } else {
                List<Message.Attachment> attachments = event.getMessage().getAttachments();
                if (attachments.isEmpty()) {
                    event.getMessage()
                            .reply("You need to supply a tune file to this command.")
                            .queue();
                    return;
                }

                //Download file to directory. No deletion needed when running on Heroku
                //as storage is not persistent (Cycles every 24hrs)
                CompletableFuture<File> future = attachments.get(0).downloadToFile("data/tunes/" +
                        attachments.get(0).getFileName());
                future.exceptionally(error -> {
                    error.printStackTrace();
                    return null;
                });
                try {
                    Decoder.run(event, future.get());
                    Decoder.changeRearWing(args[1]);
                } catch (InterruptedException | ExecutionException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if(event.getMessage().getContentRaw().startsWith("!bodykit")) {
            //Grab parameters (if any) and splits them
            String[] args = event.getMessage().getContentRaw().split("\\s+");
            if (args.length == 1) { //If command has no parameters
                event.getMessage()
                        .reply("You need to supply an index to this command.")
                        .queue();
            } else {
                List<Message.Attachment> attachments = event.getMessage().getAttachments();
                if (attachments.isEmpty()) {
                    event.getMessage()
                            .reply("You need to supply a tune file to this command.")
                            .queue();
                    return;
                }

                //Download file to directory. No deletion needed when running on Heroku
                //as storage is not persistent (Cycles every 24hrs)
                CompletableFuture<File> future = attachments.get(0).downloadToFile("data/tunes/" +
                        attachments.get(0).getFileName());
                future.exceptionally(error -> {
                    error.printStackTrace();
                    return null;
                });
                try {
                    Decoder.run(event, future.get());
                    Decoder.changeBodyKit(args[1]);
                } catch (InterruptedException | ExecutionException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if(event.getMessage().getContentRaw().startsWith("!info")) {
            //Grab parameters (if any) and splits them
            List<Message.Attachment> attachments = event.getMessage().getAttachments();
            if (attachments.isEmpty()) {
                event.getMessage()
                        .reply("You need to supply a tune file to this command.")
                        .queue();
                return;
            }
                //Download file to directory. No deletion needed when running on Heroku
                //as storage is not persistent (Cycles every 24hrs)
                CompletableFuture<File> future = attachments.get(0).downloadToFile("data/tunes/" +
                        attachments.get(0).getFileName());
                future.exceptionally(error -> {
                    error.printStackTrace();
                    return null;
                });
                try {
                    Decoder.run(event, future.get());
                    Decoder.getInfo();
                } catch (InterruptedException | ExecutionException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
}
