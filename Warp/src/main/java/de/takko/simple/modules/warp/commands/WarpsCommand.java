package de.takko.simple.modules.warp.commands;

import de.takko.simple.modules.warp.WarpModule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WarpsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (WarpModule.getWarpManager().getWarps() != null) {
            sender.sendMessage(WarpModule.getFileManager().getWithPrefix("warps.message").replaceAll("%warps%", String.join(WarpModule.getFileManager().get("warps.seperate"), WarpModule.getWarpManager().getWarps())));
        } else {
            sender.sendMessage(WarpModule.getFileManager().getWithPrefix("warps.nowarps"));
            return true;
        }
        return true;
    }
}