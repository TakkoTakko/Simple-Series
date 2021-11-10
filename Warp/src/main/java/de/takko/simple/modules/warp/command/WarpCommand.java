package de.takko.simple.modules.warp.command;

import de.takko.simple.modules.warp.WarpModule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(WarpModule.getFileManager().getWithPrefix("NoPlayer"));
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage(WarpModule.getFileManager().getWithPrefix("syntax"));
            return true;
        }
        String warp = args[0];
        if (!WarpModule.getWarpManager().exists(warp)) {
            sender.sendMessage(WarpModule.getFileManager().getWithPrefix("not_exists"));
            return true;
        }
        ((Player) sender).teleport(WarpModule.getWarpManager().getWarp(warp).getLocation());
        sender.sendMessage(WarpModule.getFileManager().getWithPrefix("teleport").replaceAll("%warp%", warp));
        return true;
    }
}