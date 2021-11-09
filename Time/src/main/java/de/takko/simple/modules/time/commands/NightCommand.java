package de.takko.simple.modules.time.commands;

import de.takko.simple.modules.time.TimeModule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NightCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(TimeModule.getFileManager().getWithPrefix("NoPlayer"));
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission(TimeModule.getFileManager().get("permissions.night"))) {
            player.sendMessage(TimeModule.getFileManager().getWithPrefix("NoPerm"));
            return true;
        }
        player.getWorld().setTime(21000L);
        player.getWorld().setThundering(false);
        player.getWorld().setStorm(false);
        player.sendMessage(TimeModule.getFileManager().getWithPrefix("messages.night"));
        return true;
    }
}