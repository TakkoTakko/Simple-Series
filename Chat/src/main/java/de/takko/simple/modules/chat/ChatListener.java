package de.takko.simple.modules.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void handle(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        if (Boolean.parseBoolean(ChatModule.getFileManager().getWithout("enabled"))) {
            if (p.hasPermission(ChatModule.getFileManager().get("owner"))) {
                event.setFormat(ChatModule.getFileManager().get("owner").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("sr_admin"))) {
                event.setFormat(ChatModule.getFileManager().get("sr_admin").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("admin"))) {
                event.setFormat(ChatModule.getFileManager().get("admin").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("developer"))) {
                event.setFormat(ChatModule.getFileManager().get("developer").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("sr_moderator"))) {
                event.setFormat(ChatModule.getFileManager().get("sr_moderator").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("moderator"))) {
                event.setFormat(ChatModule.getFileManager().get("moderator").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("supporter"))) {
                event.setFormat(ChatModule.getFileManager().get("supporter").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("t_supporter"))) {
                event.setFormat(ChatModule.getFileManager().get("t_supporter").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("builder"))) {
                event.setFormat(ChatModule.getFileManager().get("builder").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("partner"))) {
                event.setFormat(ChatModule.getFileManager().get("partner").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("youtuber"))) {
                event.setFormat(ChatModule.getFileManager().get("youtuber").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("twitch"))) {
                event.setFormat(ChatModule.getFileManager().get("twitch").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("premium_plus"))) {
                event.setFormat(ChatModule.getFileManager().get("premium_plus").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("premium"))) {
                event.setFormat(ChatModule.getFileManager().get("premium").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("spieler"))) {
                event.setFormat(ChatModule.getFileManager().get("spieler").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("user_rank_1"))) {
                event.setFormat(ChatModule.getFileManager().get("user_rank_1").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("user_rank_2"))) {
                event.setFormat(ChatModule.getFileManager().get("user_rank_2").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("user_rank_3"))) {
                event.setFormat(ChatModule.getFileManager().get("user_rank_3").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("user_rank_4"))) {
                event.setFormat(ChatModule.getFileManager().get("user_rank_4").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("user_rank_5"))) {
                event.setFormat(ChatModule.getFileManager().get("user_rank_5").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("user_rank_6"))) {
                event.setFormat(ChatModule.getFileManager().get("user_rank_6").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else if (p.hasPermission(ChatModule.getFileManager().get("user_rank_7"))) {
                event.setFormat(ChatModule.getFileManager().get("user_rank_7").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            } else {
                event.setFormat(ChatModule.getFileManager().get("spieler").replaceAll("%name%", p.getName()).replaceAll("%msg%", event.getMessage()));
            }
        }
    }
}