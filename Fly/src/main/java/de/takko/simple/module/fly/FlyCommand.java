package de.takko.simple.module.fly;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!FlyModule.getFileManager().hasPermission(player, FlyModule.getFileManager().getWithout("permission"))) {
                player.sendMessage(FlyModule.getFileManager().getWithPrefix("NoPerm"));
                return true;
            }
            if (args.length == 0) {
                if (player.getAllowFlight()) {
                    if (player.getGameMode() != GameMode.CREATIVE) {
                        player.setAllowFlight(false);
                    }
                } else {
                    player.setAllowFlight(true);
                }
            }
            else if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(FlyModule.getFileManager().getWithPrefix("OfflinePlayer"));
                    return true;
                }
                if (target.getAllowFlight()) {
                    if (target.getGameMode() != GameMode.CREATIVE) {
                        target.setAllowFlight(false);
                        //TODO: messages
                    }
                } else {
                    target.setAllowFlight(true);
                }
            } else {
                // Syntax
            }
        } else {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(FlyModule.getFileManager().getWithPrefix("OfflinePlayer"));
                    return true;
                }
                if (target.getAllowFlight()) {
                    if (target.getGameMode() != GameMode.CREATIVE) {
                        target.setAllowFlight(false);
                        //TODO: messages
                    }
                } else {
                    target.setAllowFlight(true);
                }
            }
        }
        return true;
    }
}