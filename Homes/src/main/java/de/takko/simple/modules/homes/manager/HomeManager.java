package de.takko.simple.modules.homes.manager;

import de.takko.simple.modules.homes.HomesModule;
import de.takko.simple.modules.homes.utils.Home;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class HomeManager {

    private List<Home> homes;
    private Player player;
    private File file;
    private FileConfiguration cfg;

    public HomeManager(Player player) {
        this.player = player;
        homes = HomesModule.getHomesList().get(player);
        file = new File(HomesModule.getFileManager().getModulePath() + "/homes/", player.getUniqueId() + ".yml");
        cfg = YamlConfiguration.loadConfiguration(file);
    }

    public boolean addHome(Location location, String name) {
        try {
            Home home = new Home(String.valueOf(UUID.randomUUID()), player.getUniqueId().toString(), name, location);
            homes.add(home);
            HomesModule.getHomesList().put(player, homes);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public boolean removeHome(String name) {
        try {
            homes.remove(getHome(name));
            HomesModule.getHomesList().put(player, homes);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public Home getHome(String name) {
        for (String key : cfg.getKeys(false)) {
            if (cfg.getString(key + ".name").equalsIgnoreCase(name)) {
                World world = Bukkit.getWorld(cfg.getString(key + ".loc.world"));
                double  x = cfg.getDouble(key + ".loc.x"),
                        y = cfg.getDouble(key + ".loc.y"),
                        z = cfg.getDouble(key + ".loc.z"),
                        yaw = cfg.getDouble(key + ".loc.yaw"),
                        pitch = cfg.getDouble(key + ".loc.pitch");
                Location loc = new Location(world, x, y, z, (float) yaw, (float) pitch);
                return new Home(key, cfg.getString(key + ".owner"), cfg.getString(key + ".name"), loc);
            }
        }
        return null;
    }

    public boolean exists(String name) {
        for (Home home : homes) {
            if (home.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}