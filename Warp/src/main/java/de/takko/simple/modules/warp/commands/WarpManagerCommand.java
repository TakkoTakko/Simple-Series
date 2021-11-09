package de.takko.simple.modules.warp.commands;

import de.takko.simple.modules.warp.WarpModule;
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
        Player player = (Player) sender;
        if (!WarpModule.getFileManager().hasPermission(player, WarpModule.getFileManager().get("permission"))) {
            player.sendMessage(WarpModule.getFileManager().getWithPrefix("NoPerm"));
            return true;
        }
        if (args.length != 2) {
            player.sendMessage(WarpModule.getFileManager().getWithPrefix("warpm.syntax"));
            return true;
        }
        String warp = args[1];
        if (args[0].equalsIgnoreCase("create")) {
            WarpModule.getWarpManager().addWarp(warp, ((Player) sender).getLocation());
            player.sendMessage(WarpModule.getFileManager().getWithPrefix("warpm.add").replaceAll("%warp%", warp));
        } else if (args[0].equalsIgnoreCase("remove")) {
            if (WarpModule.getWarpManager().exists(warp)) {
                WarpModule.getWarpManager().removeWarp(warp);
                player.sendMessage(WarpModule.getFileManager().getWithPrefix("warpm.remove").replaceAll("%warp%", warp));
            } else {
                player.sendMessage(WarpModule.getFileManager().getWithPrefix("warpm.not_exists"));
                return true;
            }
        } else {
            player.sendMessage(WarpModule.getFileManager().getWithPrefix("warpm.syntax"));
            return true;
        }
        return true;
    }
}