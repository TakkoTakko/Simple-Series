package de.takko.simple.manager.command;

import de.takko.simple.manager.SimpleManager;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InfoCommand implements CommandExecutor {

    private Set<SimpleModule> modules = new HashSet<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission(SimpleManager.getManagerConfig().getString("permission"))) {
            sender.sendMessage(SimpleManager.getManagerConfig().getStringWithPrefix("NoPermission"));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(SimpleManager.getManagerConfig().getStringWithPrefix("module.all.show.size").replaceAll("%module_size%", String.valueOf(SimpleManager.getModuleSet().size())));
            for (SimpleModule module : SimpleManager.getModuleSet()) {
                sender.sendMessage(Utils.replaceModulePlaceholders(module, SimpleManager.getManagerConfig().getStringWithPrefix("module.all.show.format")));
            }
            return true;
        }

        if (args.length == 1) {

        }

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("all")) {
                if (args[1].equalsIgnoreCase("names")) {
                    List<String> modules = new ArrayList<>();
                    SimpleManager.getModuleSet().forEach(module -> { modules.add(module.getModuleInfo().getName()); });
                    sender.sendMessage(Utils.translateColorCodes(SimpleManager.getManagerConfig().getStringWithPrefix("module.all.names").replaceAll("%module_all_names%", String.join(SimpleManager.getManagerConfig().getString("placeholder.separator"), modules))));
                    return true;
                }
            }
        }

        if (args.length == 3) {

        }
        return true;
    }
}