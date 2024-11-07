package de.reave.teleportrequests;

import de.reave.teleportrequests.commands.TeleportAcceptCommand;
import de.reave.teleportrequests.commands.TeleportRequestCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeleportRequest extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("teleportrequest").setExecutor(new TeleportRequestCommand());
        getCommand("teleportaccept").setExecutor(new TeleportAcceptCommand());
    }

}
