package de.takko.simple.modules.gamemode;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(GameModeModule.getFileManager().getWithPrefix("message.syntax"));
                return true;
            }
            Player player = (Player) sender;
            GameMode mode = parseGameMode(args[0]);

            if(mode == null) {
                player.sendMessage(GameModeModule.getFileManager().get("prefix") + GameModeModule.getFileManager().get("syntax"));
                return true;
            }

            if (!GameModeModule.getFileManager().hasPermission(player, GameModeModule.getFileManager().get("permission.gamemode." + mode.getValue()))) {
                sender.sendMessage(GameModeModule.getFileManager().getWithPrefix("NoPerm"));
                return true;
            }

            player.setGameMode(mode);
            sender.sendMessage(GameModeModule.getFileManager().getWithPrefix("message.self." + mode.getValue()));
            return true;
        }

        if(args.length == 2) {
            Player player = (Player) sender;
            GameMode mode = parseGameMode(args[0]);

            if(mode == null) {
                player.sendMessage(GameModeModule.getFileManager().get("prefix") + GameModeModule.getFileManager().get("message.syntax"));
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(GameModeModule.getFileManager().getWithPrefix("OfflinePlayer"));
                return true;
            }

            if (target == player) {
                player.sendMessage(GameModeModule.getFileManager().getWithPrefix("message.error"));
                return true;
            }

            if (!GameModeModule.getFileManager().hasPermission(player, GameModeModule.getFileManager().get("permission.gamemode." + mode.getValue()))) {
                player.sendMessage(GameModeModule.getFileManager().getWithPrefix("NoPerm"));
                return true;
            }

            target.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(GameModeModule.getFileManager().getWithPrefix("message.other.self." + mode.getValue()).replaceAll("%name%", target.getName()));
            target.sendMessage(GameModeModule.getFileManager().getWithPrefix("message.other.other." + mode.getValue()).replaceAll("%name%", player.getName()));
            return true;
        }
        return true;
    }

    public GameMode parseGameMode(String gamemode) {
        if (gamemode.equalsIgnoreCase("survival") || gamemode.equalsIgnoreCase("0")) {
            return GameMode.SURVIVAL;
        } else if (gamemode.equalsIgnoreCase("creative") || gamemode.equalsIgnoreCase("1")) {
            return GameMode.CREATIVE;
        } else if (gamemode.equalsIgnoreCase("adventure") || gamemode.equalsIgnoreCase("2")) {
            return GameMode.ADVENTURE;
        } else if (gamemode.equalsIgnoreCase("spectator") || gamemode.equalsIgnoreCase("3")) {
            return GameMode.SPECTATOR;
        }
        return null;
    }
}