package de.reave.teleportrequests;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;

public class Utils {
    public static String ColoredText(String pText, ChatColor pColor){
        return pColor + pText;
    }
    public static String PluginText(String pText){
        return ChatColor.DARK_PURPLE + "[TPR] " + pText;
    }
}
