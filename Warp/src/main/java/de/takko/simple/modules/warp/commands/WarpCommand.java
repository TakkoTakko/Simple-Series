package de.takko.simple.modules.warp.commands;

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
            sender.sendMessage(WarpModule.getFileManager().getWithPrefix("warp.syntax"));
            return true;
        }
        String warp = args[0];
        if (WarpModule.getWarpManager().exists(warp)) {
            ((Player) sender).teleport(WarpModule.getWarpManager().getWarp(warp));
            sender.sendMessage(WarpModule.getFileManager().getWithPrefix("warp.warp").replaceAll("%warp%", warp));
        } else {
            sender.sendMessage(WarpModule.getFileManager().getWithPrefix("warp.not_exists"));
            return true;
        }
        return true;
    }
}