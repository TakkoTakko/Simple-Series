package de.takko.simple.module.fly;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!FlyModule.getFileManager().hasPermission(sender, FlyModule.getFileManager().getWithout("permission"))) {
            sender.sendMessage(FlyModule.getFileManager().getWithPrefix("NoPerm"));
            return true;
        }

        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(FlyModule.getFileManager().getWithPrefix("NoPlayer"));
                return true;
            }
            Player player = (Player) sender;
            if (player.getGameMode() == GameMode.CREATIVE && player.getAllowFlight()) {
                return true;
            }
            player.setAllowFlight(!player.getAllowFlight());
            player.sendMessage(FlyModule.getFileManager().getWithPrefix("self"));
            return true;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(FlyModule.getFileManager().getWithPrefix("OfflinePlayer"));
                return true;
            }

            if (target.getGameMode() == GameMode.CREATIVE && target.getAllowFlight()) {
                return true;
            }
            target.setAllowFlight(!target.getAllowFlight());
            sender.sendMessage(FlyModule.getFileManager().getWithPrefix("other.self").replaceAll("%player%", target.getName()));
            if (target != sender) {
                target.sendMessage(FlyModule.getFileManager().getWithPrefix("other.other").replaceAll("%player%", sender instanceof Player ? sender.getName() : FlyModule.getFileManager().get("ConsoleReplacement")));
            }
            return true;
        }

        sender.sendMessage(FlyModule.getFileManager().getWithPrefix("syntax"));
        return true;
    }
}