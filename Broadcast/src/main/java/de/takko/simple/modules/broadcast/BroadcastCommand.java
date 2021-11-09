package de.takko.simple.modules.broadcast;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (!BroadcastModule.getFileManager().hasPermission(((Player) sender), BroadcastModule.getFileManager().get("permission"))) {
                sender.sendMessage(BroadcastModule.getFileManager().getWithPrefix("NoPerm"));
                return true;
            }
        }
        if (args.length >= 1) {
            String message = "";
            for (int i = 0; i < args.length; i++) {
                message = message + args[i] + " ";
            }
            boolean show_header_footer = Boolean.parseBoolean(BroadcastModule.getFileManager().get("header_footer"));
            if (show_header_footer) {
                Bukkit.broadcastMessage(BroadcastModule.getFileManager().get("message.header"));
            }
            Bukkit.broadcastMessage(BroadcastModule.getFileManager().get("message.message").replaceAll("%bcmsg%", message));
            if (show_header_footer) {
                Bukkit.broadcastMessage(BroadcastModule.getFileManager().get("message.footer"));
            }
        } else {
            sender.sendMessage(BroadcastModule.getFileManager().getWithPrefix("syntax"));
            return true;
        }
        return true;
    }
}