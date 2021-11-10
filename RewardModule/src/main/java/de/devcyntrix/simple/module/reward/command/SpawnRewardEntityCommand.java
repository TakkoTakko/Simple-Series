package de.devcyntrix.simple.module.reward.command;

import de.devcyntrix.simple.module.reward.RewardConfiguration;
import de.devcyntrix.simple.module.reward.RewardEntity;
import de.devcyntrix.simple.module.reward.RewardModule;
import de.devcyntrix.simple.module.reward.util.Chat;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Locale;

@RequiredArgsConstructor
public class SpawnRewardEntityCommand implements CommandExecutor {

    private final RewardModule module;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player))
            return true;

        if (args.length <= 1)
            return false;

        EntityType type;
        try {
            type = EntityType.valueOf(args[0].toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            sender.sendMessage("§cEntity type not found");
            return true;
        }

        if(!type.isAlive() || !type.isSpawnable()) {
            sender.sendMessage("§cYou have to choose a living spawnable entity.");
            return true;
        }

        String name = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        name = Chat.colorize(name);

        RewardConfiguration configuration = module.getConfiguration();

        configuration.setEntity(new RewardEntity(player.getLocation(), type, name));
        configuration.getEntity().respawn();

        module.saveConfig();
        return true;
    }
}
