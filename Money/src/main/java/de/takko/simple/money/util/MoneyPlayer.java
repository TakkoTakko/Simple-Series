package de.takko.simple.money.util;

import de.takko.simple.money.MoneyModule;
import de.takko.simple.money.util.sql.MoneyAdapter;
import de.takko.simple.money.util.sql.MoneyFile;
import org.bukkit.entity.Player;

public class MoneyPlayer {

    private final Player player;
    private static boolean mySQL;

    private MoneyAdapter moneyAdapter;
    private MoneyFile moneyFile;

    static {
        mySQL = MoneyModule.sql;
    }

    public MoneyPlayer(Player player) {
        this.player = player;
        if (mySQL) {
            moneyAdapter = new MoneyAdapter(player);
        }
        moneyFile = new MoneyFile(player);
    }

    public boolean exists() {
        if (mySQL) {
            return moneyAdapter.exists();
        }
        return moneyFile.exists();
    }

    public void createPlayer() {
        if (mySQL) {
            moneyAdapter.createPlayer();
        }
        moneyFile.createPlayer();
    }

    public double getMoney() {
        if (mySQL) {
            return moneyAdapter.getMoney();
        }
        return moneyFile.getMoney();
    }

    public void setMoney(double amount) {
        if (mySQL) {
            moneyAdapter.setMoney(amount);
        }
        moneyFile.setMoney(amount);
    }

    public void addMoney(double amount) {
        if (mySQL) {
            moneyAdapter.addMoney(amount);
        }
        moneyFile.addMoney(amount);
    }

    public void removeMoney(double amount) {
        if (mySQL) {
            moneyAdapter.removeMoney(amount);
        }
        moneyFile.removeMoney(amount);
    }
}