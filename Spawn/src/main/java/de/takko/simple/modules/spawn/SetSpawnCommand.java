package de.takko.simple.modules.spawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(SpawnModule.getFileManager().getWithPrefix("NoPlayer"));
            return true;
        }
        Player player = (Player) sender;
        if (!SpawnModule.getFileManager().hasPermission(player, SpawnModule.getFileManager().get("permission"))) {
            player.sendMessage(SpawnModule.getFileManager().getWithPrefix("NoPerm"));
            return true;
        }
        SpawnModule.save(player.getLocation());
        SpawnModule.spawn = player.getLocation();
        player.sendMessage(SpawnModule.getFileManager().getWithPrefix("SpawnSet"));
        return true;
    }
}