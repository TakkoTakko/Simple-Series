package de.takko.simple.modules.enderchest;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderchestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!(sender instanceof Player)) {
            sender.sendMessage(EnderchestModule.getFileManager().getWithPrefix("NoPlayer"));
            return true;
        }
        if (!EnderchestModule.getFileManager().hasPermission(player, EnderchestModule.getFileManager().get("permission"))) {
            sender.sendMessage(EnderchestModule.getFileManager().getWithPrefix("NoPerm"));
            return true;
        }
        if (args.length == 0) {
            player.openInventory(player.getEnderChest());
        }
        else if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                player.openInventory(target.getEnderChest());
            } else {
                player.sendMessage(EnderchestModule.getFileManager().getWithPrefix("OfflinePlayer"));
            }
        }
        return true;
    }
}