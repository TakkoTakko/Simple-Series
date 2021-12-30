package de.takko.simple.module.homes.manager;

import de.takko.simple.manager.base.util.Utils;
import de.takko.simple.module.homes.HomesModule;
import de.takko.simple.module.homes.util.Home;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeManager {

    private List<Home> homes = new ArrayList<>();
    private Player player;
    private File file;
    private FileConfiguration cfg;

    public HomeManager(Player player) {
        this.player = player;
        new File(HomesModule.getFileManager().getModulePath() + "/homes/").mkdirs();

        if (HomesModule.getHomesList().containsKey(player)) {
            homes = HomesModule.getHomesList().get(player);
        }

        file = new File(HomesModule.getFileManager().getModulePath() + "/homes/", player.getUniqueId() + ".yml");
        cfg = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            try {
                file.createNewFile();
                cfg.options().header("Homes from: " + player.getName() + " | UUID: " + player.getUniqueId());
                Utils.saveFile(file, cfg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addHome(Location location, String name) {
        Home home = new Home(String.valueOf(UUID.randomUUID()), player.getUniqueId().toString(), name, location);
        homes.add(home);
        HomesModule.getHomesList().put(player, homes);
    }

    public void addHome(Home home) {
        homes.add(home);
        HomesModule.getHomesList().put(player, homes);
    }

    public boolean removeHome(Home home) {
        try {
            homes.remove(home);
            HomesModule.getHomesList().put(player, homes);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public Home getHome(String name) {
        for (Home home : HomesModule.getHomesList().get(player)) {
            if (home.getName().equalsIgnoreCase(name)) {
                return home;
            }
        }
        return null;
    }

    public void loadHomes() {
        if (!file.exists()) {
            return;
        }
        if (file.length() == 0) {
            return;
        }
        for (String key : cfg.getKeys(false)) {

            World world = Bukkit.getWorld(cfg.getString(key + ".loc.world"));
            double x = cfg.getDouble(key + ".loc.x"),
                    y = cfg.getDouble(key + ".loc.y"),
                    z = cfg.getDouble(key + ".loc.z"),
                    yaw = cfg.getDouble(key + ".loc.yaw"),
                    pitch = cfg.getDouble(key + ".loc.pitch");
            Location loc = new Location(world, x, y, z, (float) yaw, (float) pitch);

            String owner = cfg.getString(key + ".owner");
            String name = cfg.getString(key + ".name");

            homes.add(new Home(key, owner, name, loc));
        }
        if (HomesModule.getHomesList().containsKey(player)) {
            HomesModule.getHomesList().remove(player);
        }
        HomesModule.getHomesList().put(player, homes);
    }

    public void saveHomes() {
        if (HomesModule.getHomesList().containsKey(player)) {
            try {
                for (Home home : homes) {
                    cfg.set(home.getUniqueID() + ".name", home.getName());
                    cfg.set(home.getUniqueID() + ".owner", home.getOwner());

                    cfg.set(home.getUniqueID() + ".loc.world", home.getLocation().getWorld().getName());
                    cfg.set(home.getUniqueID() + ".loc.x", home.getLocation().getX());
                    cfg.set(home.getUniqueID() + ".loc.y", home.getLocation().getY());
                    cfg.set(home.getUniqueID() + ".loc.z", home.getLocation().getZ());
                    cfg.set(home.getUniqueID() + ".loc.yaw", home.getLocation().getYaw());
                    cfg.set(home.getUniqueID() + ".loc.pitch", home.getLocation().getX());
                }
                Utils.saveFile(file, cfg);
            } finally {
                HomesModule.getHomesList().remove(player);
            }
        }
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