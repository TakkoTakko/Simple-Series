package de.takko.simple.module.spawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(SpawnModule.getFileManager().getWithPrefix("NoPlayer"));
            return true;
        }
        Player player = (Player) sender;
        if (!SpawnModule.isSpawnSet()) {
            player.sendMessage(SpawnModule.getFileManager().getWithPrefix("NoSpawn"));
            return true;
        }
        player.teleport(SpawnModule.spawn);
        player.sendMessage(SpawnModule.getFileManager().getWithPrefix("teleport"));
        return true;
    }
}