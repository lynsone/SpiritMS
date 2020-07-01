package bot.event;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import server.ShutdownServer;

public class ShutDownHandler extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        if(e.getMessage().getContentRaw().equalsIgnoreCase("!shutdown")){
            if(!e.getMember().hasPermission(Permission.ADMINISTRATOR)){
                e.getChannel().sendMessage("You do not have the proper permissions to use this command").queue();
                return;
            }
            Thread t = null;

            if (t == null || !t.isAlive()) {
                e.getChannel().sendMessage("Server now shutting down.").queue();
                e.getGuild().getTextChannelById("722160746565468291").sendMessage("Server is down.").queue();
                t = new Thread(ShutdownServer.getInstance());
                ShutdownServer.getInstance().shutdown();
                t.start();
                return;
            } else {
                e.getChannel().sendMessage("You do not have the proper permissions to use this command").queue();
                return;
            }
        }
    }
}
