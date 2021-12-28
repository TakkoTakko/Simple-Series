package de.takko.simple.money.util.sql;

import de.takko.simple.manager.base.util.Logger;
import de.takko.simple.manager.base.util.MySQL;
import de.takko.simple.money.MoneyModule;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyAdapter {

    private final Player player;
    private static MySQL mySQL;
    private static Logger logger;

    static {
        mySQL = MoneyModule.getMySQL();
        logger = new Logger();
    }

    public MoneyAdapter(Player player) {
        this.player = player;
    }

    public boolean exists() {
        if (mySQL.isConnected()) {
            try {
                ResultSet rs = mySQL.getResult("SELECT * FROM MONEY WHERE UUID='" + player.getUniqueId() + "'");
                if (rs.next()) {
                    return (rs.getString("UUID") != null);
                }
            } catch (SQLException e) {
                logger.custom(Logger.ModuleType.MYSQL, "&cError while getting Player" + "\n" + e.getMessage());
            }
        }
        return false;
    }

    public void createPlayer() {
        if (!exists()) {
            mySQL.update("INSERT INTO MONEY (UUID, MONEY) VALUES ('" + player.getUniqueId() + "', '" + Integer.valueOf(MoneyModule.getFileManager().getWithout("defaultMoney")) + "');");
        }
    }

    public Integer getMoney() {
        Integer money = null;
        if (exists()) {
            try {
                ResultSet rs = mySQL.getResult("SELECT * FROM MONEY WHERE UUID='" + player.getUniqueId() + "'");
                if (rs.next()) {
                    Integer.valueOf(rs.getInt("MONEY"));
                }
                money = rs.getInt("MONEY");
            } catch (SQLException e) {
                logger.custom(Logger.ModuleType.MYSQL, "§cError while getting Money from §e" + player.getName() + "§r\n" + e.getMessage());
            }
        } else {
            createPlayer();
            getMoney();
        }
        return money;
    }
    public void setMoney(double amount) {
        if (exists()) {
            mySQL.update("UPDATE MONEY SET MONEY='" + amount + "' WHERE UUID='" + player.getUniqueId() + "'");
        } else {
            createPlayer();
            setMoney(amount);
        }
    }
    public void addMoney(double amount) {
        if (exists()) {
            setMoney(getMoney() + amount);
        } else {
            createPlayer();
            addMoney(amount);
        }
    }
    public void removeMoney(double amount) {
        if (exists()) {
            if (getMoney() - amount <= 0) {
                setMoney(0);
                return;
            }
            setMoney(getMoney()- amount);
        } else {
            createPlayer();
            removeMoney(amount);
        }
    }
}