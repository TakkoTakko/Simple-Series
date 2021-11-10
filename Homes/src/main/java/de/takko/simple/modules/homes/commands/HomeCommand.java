package de.takko.simple.modules.homes.commands;

import de.takko.simple.modules.homes.HomesModule;
import de.takko.simple.modules.homes.manager.HomeManager;
import de.takko.simple.modules.homes.utils.Home;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(HomesModule.getFileManager().getWithPrefix("NoPlayer"));
            return true;
        }
        Player player = (Player) sender;
        HomeManager homeManager = new HomeManager(player);
        if (args.length == 1) {
            String name = args[0];
            if (!homeManager.exists(name)) {
                player.sendMessage(HomesModule.getFileManager().getWithPrefix("home.not_exists"));
                return true;
            }
            Home home = homeManager.getHome(name);
            player.teleport(home.getLocation());
            player.sendMessage(HomesModule.getFileManager().getWithPrefix("home.teleport").replaceAll("%home%", home.getName()));
        }
        else if (args.length == 2) {
            String name = args[1];
            if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("create")) {
                if (homeManager.exists(name)) {
                    player.sendMessage(HomesModule.getFileManager().getWithPrefix("already_exists"));
                    return true;
                }
                homeManager.addHome(player.getLocation(), name);
                player.sendMessage(HomesModule.getFileManager().getWithPrefix("home.add").replaceAll("%home%", name));
            }
            else if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete")) {
                if (!homeManager.exists(name)) {
                    player.sendMessage(HomesModule.getFileManager().getWithPrefix("home.not_exists"));
                    return true;
                }
                homeManager.removeHome(name);
                player.sendMessage(HomesModule.getFileManager().getWithPrefix("home.remove").replaceAll("%home%", name));
            }
            else if (args[0].equalsIgnoreCase("update") || args[0].equalsIgnoreCase("change")) {
                if (!homeManager.exists(name)) {
                    player.sendMessage(HomesModule.getFileManager().getWithPrefix("home.not_exists"));
                    return true;
                }
                homeManager.removeHome(name);
                homeManager.addHome(player.getLocation(), name);
                player.sendMessage(HomesModule.getFileManager().getWithPrefix("home.update").replaceAll("%home%", name));
            }
            else {
                player.sendMessage(HomesModule.getFileManager().getWithPrefix("syntax"));
                return true;            }
        }
        return true;
    }
}