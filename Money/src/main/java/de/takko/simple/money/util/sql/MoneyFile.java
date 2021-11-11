package de.takko.simple.money.util.sql;

import de.takko.simple.money.MoneyModule;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class MoneyFile {

    private static File file;
    private static FileConfiguration cfg;
    private final Player player;

    static {
        file = new File(MoneyModule.getFileManager().getModulePath(), "money.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
    }

    public MoneyFile(Player player) {
        this.player = player;
    }

    public void createPlayer() {
        if (cfg.getString(player.getUniqueId().toString()) == null) {
            cfg.set(player.getUniqueId() + "", Integer.valueOf(MoneyModule.getFileManager().getWithout("defaultMoney")));
            save();
        }
    }

    public boolean exists() {
        return cfg.getString(player.getUniqueId().toString()) != null;
    }

    public Integer getMoney() {
        if (exists()) {
            return cfg.getInt(player.getUniqueId().toString());
        } else {
            createPlayer();
            return getMoney();
        }
    }

    public void setMoney(int amount) {
        if (exists()) {
            cfg.set(player.getUniqueId().toString(), amount);
            save();
        } else {
            createPlayer();
            setMoney(amount);
        }
    }

    public void addMoney(int amount) {
        if (exists()) {
            cfg.set(player.getUniqueId().toString(), getMoney() + amount);
            save();
        } else {
            createPlayer();
            addMoney(amount);
        }
    }

    public void removeMoney(int amount) {
        if (exists()) {
            if (getMoney() - amount <= 0) {
                cfg.set(player.getUniqueId().toString(), 0);
                save();
                return;
            }
            cfg.set(player.getUniqueId().toString(), getMoney() - amount);
            save();
        } else {
            createPlayer();
            removeMoney(amount);
        }
    }

    private void save() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}