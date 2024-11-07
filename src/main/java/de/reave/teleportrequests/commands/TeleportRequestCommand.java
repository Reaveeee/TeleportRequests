package de.reave.teleportrequests.commands;

import de.reave.teleportrequests.Utils;
import de.reave.teleportrequests.data.DataManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.reave.teleportrequests.Utils.ColoredText;
import static de.reave.teleportrequests.Utils.PluginText;

public class TeleportRequestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
        //Errors
        if(!(sender instanceof Player)){
            sender.sendMessage(PluginText(ColoredText("Error: You are no player", ChatColor.RED)));
            return true;
        }
        if(args.length != 1){
            sender.sendMessage(PluginText(ColoredText("Error: You have to enter a name", ChatColor.RED)));
            return true;
        }
        if(!sender.getServer().getOnlinePlayers().contains(sender.getServer().getPlayer(args[0]))){
            sender.sendMessage(PluginText(ColoredText("Error: This player isn't online", ChatColor.RED)));
            return true;
        }
        if(args[0].equals(sender.getName())){
            sender.sendMessage(PluginText(ColoredText("Error: You can't teleport to yourself", ChatColor.RED)));
            return true;
        }

        //Execution
        Player player = (Player) sender;
        Player receiver = player.getServer().getPlayer(args[0]);

        player.sendMessage(PluginText(ColoredText("Sent teleport request", ChatColor.GREEN)));
        DataManager.lastRequest.put(receiver.getName(), player.getName());
        TextComponent acceptText = new TextComponent(PluginText(ColoredText("  ACCEPT", ChatColor.GREEN)));
        acceptText.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GREEN + "Click to accept the request").create()));
        acceptText.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/accept"));

        receiver.sendMessage(PluginText(ColoredText("//------------------------------------\\\\", ChatColor.GRAY)));
        receiver.sendMessage(PluginText(ColoredText("  " + sender.getName(), ChatColor.LIGHT_PURPLE) + ColoredText(" wants to teleport to your location", ChatColor.GRAY)));
        receiver.spigot().sendMessage(acceptText);
        receiver.sendMessage(PluginText(ColoredText("\\\\------------------------------------//", ChatColor.GRAY)));
        return true;
    }
}
