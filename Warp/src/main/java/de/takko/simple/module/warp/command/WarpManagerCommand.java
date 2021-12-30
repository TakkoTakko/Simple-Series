package de.takko.simple.module.warp.command;

import de.takko.simple.module.warp.WarpModule;
import de.takko.simple.module.warp.util.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpManagerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(WarpModule.getFileManager().getWithPrefix("NoPlayer"));
            return true;
        }
        if (args.length == 2) {
            String warpName = args[1];
            if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("create")) {
                if (WarpModule.getWarpManager().exists(warpName)) {
                    sender.sendMessage(WarpModule.getFileManager().getWithPrefix("name_already_used"));
                    return true;
                }
                WarpModule.getWarpManager().addWarp(((Player) sender).getLocation(), warpName);
                sender.sendMessage(WarpModule.getFileManager().getWithPrefix("add").replaceAll("%warp%", warpName));
            }
            else if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete")) {
                if (!WarpModule.getWarpManager().exists(warpName)) {
                    sender.sendMessage(WarpModule.getFileManager().getWithPrefix("not_exists"));
                    return true;
                }
                Warp warp = WarpModule.getWarpManager().getWarp(warpName);
                WarpModule.getWarpManager().removeWarp(warp);
                sender.sendMessage(WarpModule.getFileManager().getWithPrefix("remove").replaceAll("%warp%", warp.getName()));
            }
            else if (args[0].equalsIgnoreCase("update") || args[0].equalsIgnoreCase("change")) {
                if (!WarpModule.getWarpManager().exists(warpName)) {
                    sender.sendMessage(WarpModule.getFileManager().getWithPrefix("not_exists"));
                    return true;
                }
                Warp warp = WarpModule.getWarpManager().getWarp(warpName);
                WarpModule.getWarpManager().removeWarp(warp);
                WarpModule.getWarpManager().addWarp(((Player) sender).getLocation(), warpName);
                sender.sendMessage(WarpModule.getFileManager().getWithPrefix("update").replaceAll("%warp%", warp.getName()));
            }
            else {
                sender.sendMessage(WarpModule.getFileManager().getWithPrefix("syntax"));
                return true;
            }
        }
        return true;
    }
}