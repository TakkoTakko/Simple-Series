package de.takko.simple.money.command;

import de.takko.simple.money.MoneyModule;
import de.takko.simple.money.util.MoneyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MoneyCommand implements CommandExecutor {

    private final MoneyModule module;

    public MoneyCommand(MoneyModule module) {
        this.module = module;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(MoneyModule.getFileManager().getWithPrefix("NoPlayer"));
            return true;
        }

        Player player = (Player) sender;
        MoneyPlayer moneyPlayer = new MoneyPlayer(player);
        if (args.length == 0) {
            player.sendMessage(MoneyModule.getFileManager().getWithPrefix("showMoney").replaceAll("%money%", String.valueOf(moneyPlayer.getMoney())));
            return true;
        }

        if (!MoneyModule.getFileManager().hasPermission(sender, MoneyModule.getFileManager().getWithout("permission"))) {
            sender.sendMessage(MoneyModule.getFileManager().getWithPrefix("NoPerm"));
            return true;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(MoneyModule.getFileManager().getWithPrefix("OfflinePlayer"));
                return true;
            }
            //TODO: andere nachrciht
            sender.sendMessage(MoneyModule.getFileManager().getWithPrefix("showMoney").replaceAll("%money%", String.valueOf(new MoneyPlayer(target).getMoney())));
            return true;
        }
        if (args.length == 3) {
            Player target = Bukkit.getPlayer(args[0]);
            double amount = Double.parseDouble(args[2]);
            MoneyPlayer targetMoneyPlayer = new MoneyPlayer(target);
            if (target == null) {
                sender.sendMessage(MoneyModule.getFileManager().getWithPrefix("OfflinePlayer"));
                return true;
            }
            switch (args[1]) {
                case "set":
                    sender.sendMessage(MoneyModule.getFileManager().getWithPrefix("setMoney").replaceAll("%player%", target.getName()).replaceAll("%money%", String.valueOf(amount)));
                    targetMoneyPlayer.setMoney(amount);
                    break;
                case "add":
                    sender.sendMessage(MoneyModule.getFileManager().getWithPrefix("addMoney").replaceAll("%player%", target.getName()).replaceAll("%money%", String.valueOf(amount)));
                    targetMoneyPlayer.addMoney(amount);
                    break;
                case "remove":
                    sender.sendMessage(MoneyModule.getFileManager().getWithPrefix("removeMoney").replaceAll("%player%", target.getName()).replaceAll("%money%", String.valueOf(amount)));
                    targetMoneyPlayer.removeMoney(amount);
                    break;
                default:
                    sender.sendMessage(MoneyModule.getFileManager().getWithPrefix("syntax"));
                    break;
            }
            return true;
        }
        sender.sendMessage(MoneyModule.getFileManager().getWithPrefix("syntax"));
        return true;
    }
}