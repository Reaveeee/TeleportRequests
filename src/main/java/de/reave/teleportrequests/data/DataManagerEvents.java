package de.reave.teleportrequests.data;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DataManagerEvents implements Listener {
    @EventHandler
    public void OnPlayerQuit(PlayerQuitEvent event){
        DataManager.lastRequest.remove(event.getPlayer().getName());
    }
}
