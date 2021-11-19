package de.takko.simple.module.join_quit_message;

import de.takko.simple.manager.util.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JQListener implements Listener {

    @EventHandler
    public void handle(PlayerJoinEvent event) {
        if (Boolean.parseBoolean(Join_Quit_MessageModule.getFileManager().get("join.enabled"))) {
            event.setJoinMessage(Utils.translateColorCodes(Join_Quit_MessageModule.getFileManager().get("join.message").replaceAll("%prefix%", Join_Quit_MessageModule.getFileManager().get("Prefix")).replaceAll("%player%", event.getPlayer().getName())));
        }
    }

    @EventHandler
    public void handle(PlayerQuitEvent event) {
        if (Boolean.parseBoolean(Join_Quit_MessageModule.getFileManager().get("quit.enabled"))) {
            event.setQuitMessage(Utils.translateColorCodes(Join_Quit_MessageModule.getFileManager().get("quit.message").replaceAll("%prefix%", Join_Quit_MessageModule.getFileManager().get("Prefix")).replaceAll("%player%", event.getPlayer().getName())));
        }
    }
}