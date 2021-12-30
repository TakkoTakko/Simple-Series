package de.takko.simple.module.spawn;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpawnListener implements Listener {

    @EventHandler
    public void handle(PlayerJoinEvent event) {
        if (Boolean.parseBoolean(SpawnModule.getFileManager().get("teleportOnJoin"))) {
            if (SpawnModule.isSpawnSet()) {
                event.getPlayer().teleport(SpawnModule.spawn);
            }
        }
    }
}