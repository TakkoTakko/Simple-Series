package de.takko.simple.modules.homes.commands;

import com.google.common.base.Joiner;
import de.takko.simple.modules.homes.HomesModule;
import de.takko.simple.modules.homes.utils.Home;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HomesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(HomesModule.getFileManager().getWithPrefix("NoPlayer"));
        }
        Player player = (Player) sender;
        if (args.length != 0) {
            sender.sendMessage(HomesModule.getFileManager().getWithPrefix("syntax"));
        }
        List<String> homes = new ArrayList<>();
        for (Home home : HomesModule.getHomesList().get(player)) {
            homes.add(home.getName());
        }
        player.sendMessage(HomesModule.getFileManager().getWithPrefix("homes.format").replaceAll("%homes%", Joiner.on(HomesModule.getFileManager().get("homes.seperator")).join(homes)));
        return true;
    }
}