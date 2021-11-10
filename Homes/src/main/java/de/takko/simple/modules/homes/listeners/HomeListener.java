package de.takko.simple.modules.homes.listeners;

import de.takko.simple.modules.homes.manager.HomeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class HomeListener implements Listener {

    @EventHandler
    public void handle(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        new HomeManager(player).loadHomes();
    }

    @EventHandler
    public void handle(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        new HomeManager(player).saveHomes();
    }
}