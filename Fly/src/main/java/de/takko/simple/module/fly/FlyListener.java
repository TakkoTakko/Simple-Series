package de.takko.simple.module.fly;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FlyListener implements Listener {

    @EventHandler
    public void handle(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (FlyModule.getLoggedIn().contains(player)) {
            player.setAllowFlight(true);
        }
    }
}