package de.reave.teleportrequests.commands;

import de.reave.teleportrequests.data.DataManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.reave.teleportrequests.Utils.ColoredText;
import static de.reave.teleportrequests.Utils.PluginText;

public class TeleportAcceptCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
        //Errors
        if(!(sender instanceof Player)){
            sender.sendMessage(PluginText("Error: You are no player"));
            return true;
        }
        String requesterName = DataManager.lastRequest.get(sender.getName()); //Execution
        if(!sender.getServer().getOnlinePlayers().contains(sender.getServer().getPlayer(requesterName))){
            sender.sendMessage(PluginText(ColoredText("Error: This player isn't online", ChatColor.RED)));
            return true;
        }
        if(args.length > 0){
            sender.sendMessage(PluginText(ColoredText("Error: There are no arguments for this command", ChatColor.RED)));
            return true;
        }

        //Execution
        Player player = (Player) sender;

        if(requesterName.length() < 4){
            sender.sendMessage(PluginText(ColoredText("No active teleport request", ChatColor.RED)));
            return true;
        }

        Player requester = player.getServer().getPlayer(requesterName);
        requester.teleport(player.getLocation());
        DataManager.lastRequest.put(player.getName(), "");
        player.sendMessage(PluginText(ColoredText("Teleported player", ChatColor.GREEN)));

        return true;
    }
}
