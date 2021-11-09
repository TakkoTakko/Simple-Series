package de.takko.simple.modules.gamemode;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                    if (!GamemodeModule.getFileManager().hasPermission(p, GamemodeModule.getFileManager().get("permission.gamemode.0"))) {
                        p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("NoPerm"));
                        return true;
                    }
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.self.0"));
                } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                    if (!GamemodeModule.getFileManager().hasPermission(p, GamemodeModule.getFileManager().get("permission.gamemode.1"))) {
                        p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("NoPerm"));
                        return true;
                    }
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.self.1"));
                } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                    if (!GamemodeModule.getFileManager().hasPermission(p, GamemodeModule.getFileManager().get("permission.gamemode.2"))) {
                        p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("NoPerm"));
                        return true;
                    }
                    p.setGameMode(GameMode.ADVENTURE);
                    p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.self.2"));
                } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                    if (!GamemodeModule.getFileManager().hasPermission(p, GamemodeModule.getFileManager().get("permission.gamemode.3"))) {
                        p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("NoPerm"));
                        return true;
                    }
                    p.setGameMode(GameMode.SPECTATOR);
                    p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.self.3"));
                } else {
                    if (GamemodeModule.getFileManager().hasPermission(p, GamemodeModule.getFileManager().get("permission.gamemode.0")) ||
                            GamemodeModule.getFileManager().hasPermission(p, GamemodeModule.getFileManager().get("permission.gamemode.1")) ||
                            GamemodeModule.getFileManager().hasPermission(p, GamemodeModule.getFileManager().get("permission.gamemode.2")) ||
                            GamemodeModule.getFileManager().hasPermission(p, GamemodeModule.getFileManager().get("permission.gamemode.3"))) {
                        p.sendMessage(GamemodeModule.getFileManager().get("prefix") + GamemodeModule.getFileManager().get("syntax"));
                    } else {
                        p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("NoPerm"));
                    }
                }
            } else if (args.length == 2) {
                Player t = Bukkit.getPlayer(args[1]);
                if (t == p) {
                    p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.error"));
                    return true;
                }
                if (t == null) {
                    p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("OfflinePlayer"));
                    return true;
                }

                if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                    if (!GamemodeModule.getFileManager().hasPermission(p, GamemodeModule.getFileManager().get("permission.gamemode.0"))) {
                        p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("NoPerm"));
                        return true;
                    }
                    t.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.other.self.0").replaceAll("%name%", t.getName()));
                    t.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.other.other.0").replaceAll("%name%", p.getName()));
                } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                    if (!GamemodeModule.getFileManager().hasPermission(p, GamemodeModule.getFileManager().get("permission.gamemode.1"))) {
                        p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("NoPerm"));
                        return true;
                    }
                    t.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.other.self.1").replaceAll("%name%", t.getName()));
                    t.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.other.other.1").replaceAll("%name%", p.getName()));
                } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                    if (!GamemodeModule.getFileManager().hasPermission(p, GamemodeModule.getFileManager().get("permission.gamemode.2"))) {
                        p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("NoPerm"));
                        return true;
                    }
                    t.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.other.self.2").replaceAll("%name%", t.getName()));
                    t.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.other.other.2").replaceAll("%name%", p.getName()));
                } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                    if (!GamemodeModule.getFileManager().hasPermission(p, GamemodeModule.getFileManager().get("permission.gamemode.3"))) {
                        p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("NoPerm"));
                        return true;
                    }
                    t.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.other.self.3").replaceAll("%name%", t.getName()));
                    t.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.other.other.3").replaceAll("%name%", p.getName()));
                } else {
                    p.sendMessage(GamemodeModule.getFileManager().get("prefix") + GamemodeModule.getFileManager().get("message.syntax"));
                    return true;
                }
            } else {
                p.sendMessage(GamemodeModule.getFileManager().get("prefix") + GamemodeModule.getFileManager().get("message.syntax"));
                return true;
            }
        } else {
            if (args.length == 2) {
                Player t = Bukkit.getPlayer(args[1]);
                if (t == null) {
                    sender.sendMessage(GamemodeModule.getFileManager().getWithPrefix("OfflinePlayer"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                    t.setGameMode(GameMode.SURVIVAL);
                    sender.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.other.self.0").replaceAll("%name%", t.getName()));
                    t.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.other.other.0").replaceAll("%name%", GamemodeModule.getFileManager().get("ConsoleReplacement")));
                } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                    t.setGameMode(GameMode.CREATIVE);
                    sender.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.other.self.1").replaceAll("%name%", t.getName()));
                    t.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.other.other.1").replaceAll("%name%", GamemodeModule.getFileManager().get("ConsoleReplacement")));
                } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                    t.setGameMode(GameMode.ADVENTURE);
                    sender.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.other.self.2").replaceAll("%name%", t.getName()));
                    t.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.other.other.2").replaceAll("%name%", GamemodeModule.getFileManager().get("ConsoleReplacement")));
                } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                    t.setGameMode(GameMode.SPECTATOR);
                    sender.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.other.self.3").replaceAll("%name%", t.getName()));
                    t.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.other.other.3").replaceAll("%name%", GamemodeModule.getFileManager().get("ConsoleReplacement")));
                } else {
                    sender.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.syntax"));
                    return true;
                }
            } else {
                sender.sendMessage(GamemodeModule.getFileManager().getWithPrefix("message.syntax"));
                return true;
            }
        }
        return true;
    }
}