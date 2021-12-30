package de.takko.simple.module.backpack;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BrokenBarrierException;

public class BackpackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(BackpackModule.getFileManager().getWithPrefix("NoPlayer"));
            return true;
        }
        Player player = (Player) sender;
        if (!BackpackModule.getFileManager().hasPermission(player, BackpackModule.getFileManager().get("permission"))) {
            player.sendMessage(BackpackModule.getFileManager().getWithPrefix("NoPerm"));
            return true;
        }

        return true;
    }
}
