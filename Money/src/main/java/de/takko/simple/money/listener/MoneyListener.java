package de.takko.simple.money.listener;

import de.takko.simple.money.MoneyModule;
import de.takko.simple.money.util.MoneyPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MoneyListener implements Listener {

    @EventHandler
    public void handle(PlayerJoinEvent event) {
        MoneyPlayer player = new MoneyPlayer(event.getPlayer());
        player.createPlayer();
    }
}