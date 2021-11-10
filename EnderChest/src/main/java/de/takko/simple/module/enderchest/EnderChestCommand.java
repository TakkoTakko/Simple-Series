package de.takko.simple.module.enderchest;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderChestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(EnderChestModule.getFileManager().getWithPrefix("NoPlayer"));
            return true;
        }
        Player player = (Player) sender;
        if (!EnderChestModule.getFileManager().hasPermission(player, EnderChestModule.getFileManager().get("permission"))) {
            sender.sendMessage(EnderChestModule.getFileManager().getWithPrefix("NoPerm"));
            return true;
        }
        if (args.length == 0) {
            player.openInventory(player.getEnderChest());
            return true;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null) {
                player.sendMessage(EnderChestModule.getFileManager().getWithPrefix("OfflinePlayer"));
                return true;
            }
            player.openInventory(target.getEnderChest());
            return true;
        }
        return true;
    }
}