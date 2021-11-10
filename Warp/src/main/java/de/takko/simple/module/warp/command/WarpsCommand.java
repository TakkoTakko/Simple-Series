package de.takko.simple.module.warp.command;

import de.takko.simple.module.warp.WarpModule;
import de.takko.simple.module.warp.util.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.stream.Collectors;

public class WarpsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<String> warps = WarpModule.getWarpList().stream().map(Warp::getName).collect(Collectors.toList());
        if (WarpModule.getWarpList().isEmpty()) {
            sender.sendMessage(WarpModule.getFileManager().getWithPrefix("NoWarps"));
            return true;
        }
        sender.sendMessage(WarpModule.getFileManager().getWithPrefix("warps").replaceAll("%warps%", String.join(WarpModule.getFileManager().get("separator"), warps)));
        return true;
    }
}