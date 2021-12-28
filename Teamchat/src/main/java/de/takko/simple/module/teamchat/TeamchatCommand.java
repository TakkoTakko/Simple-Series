package de.takko.simple.module.teamchat;

import de.takko.simple.manager.base.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamchatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!TeamchatModule.getFileManager().hasPermission(p, TeamchatModule.getFileManager().get("permission"))) {
                p.sendMessage(TeamchatModule.getFileManager().getWithPrefix("NoPerm"));
                return true;
            }
        }
        proceed(sender, args);
        return true;
    }

    private void proceed(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(TeamchatModule.getFileManager().getWithPrefix("syntax"));
            return;
        }
        String message = String.join(" ", args);
        message = Utils.translateColorCodes(message);
        for (Player p1 : Bukkit.getOnlinePlayers()) {
            if (p1.hasPermission(TeamchatModule.getFileManager().get("permission"))) {
                if (sender instanceof Player) {
                    p1.sendMessage(TeamchatModule.getFileManager().get("format").replaceAll("%prefix%", TeamchatModule.getFileManager().get("Prefix")).replaceAll("%player%", sender.getName()).replaceAll("%msg%", message));
                    Bukkit.getConsoleSender().sendMessage(TeamchatModule.getFileManager().get("format").replaceAll("%prefix%", TeamchatModule.getFileManager().get("Prefix")).replaceAll("%player%", sender.getName()).replaceAll("%msg%", message));
                } else {
                    p1.sendMessage(TeamchatModule.getFileManager().get("format").replaceAll("%prefix%", TeamchatModule.getFileManager().get("Prefix")).replaceAll("%player%", TeamchatModule.getFileManager().get("console_replacement")).replaceAll("%msg%", message));
                    Bukkit.getConsoleSender().sendMessage(TeamchatModule.getFileManager().get("format").replaceAll("%prefix%", TeamchatModule.getFileManager().get("Prefix")).replaceAll("%player%", TeamchatModule.getFileManager().get("console_replacement")).replaceAll("%msg%", message));
                }
            }
        }
    }
}