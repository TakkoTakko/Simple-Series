package de.takko.simple.modules.warp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WarpManager {

    private File file;
    private FileConfiguration cfg;
    public List<String> warps = new ArrayList<>();

    public WarpManager() {
        file = new File(WarpModule.getFileManager().getModulePath(), "warps.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        load();
    }

    public WarpManager addWarp(String name, Location location) {

        cfg.set("WARPS." + name + ".world", location.getWorld().getName());
        cfg.set("WARPS." + name + ".x", location.getX());
        cfg.set("WARPS." + name + ".y", location.getY());
        cfg.set("WARPS." + name + ".z", location.getZ());
        cfg.set("WARPS." + name + ".yaw", location.getYaw());
        cfg.set("WARPS." + name + ".pitch", location.getPitch());

        save();

        return this;
    }

    public WarpManager removeWarp(String name) {

        cfg.set("WARPS." + name + ".world", null);
        cfg.set("WARPS." + name + ".x", null);
        cfg.set("WARPS." + name + ".y", null);
        cfg.set("WARPS." + name + ".z", null);
        cfg.set("WARPS." + name + ".yaw", null);
        cfg.set("WARPS." + name + ".pitch", null);
        cfg.set("WARPS." + name, null);

        save();

        return this;
    }

    public Location getWarp(String name) {
        World world = Bukkit.getWorld(cfg.getString("WARPS." + name + ".world"));
        double
                x = cfg.getDouble("WARPS." + name + ".x"),
                y = cfg.getDouble("WARPS." + name + ".y"),
                z = cfg.getDouble("WARPS." + name + ".z"),
                yaw = cfg.getDouble("WARPS." + name + ".yaw"),
                pitch = cfg.getDouble("WARPS." + name + ".pitch");
        return new Location(world, x, y, z, (float) yaw, (float) pitch);
    }

    public boolean exists(String name) {

        if (cfg.get("WARPS." + name) != null) {
            return true;
        } else {
            return false;
        }


        //return warps.stream().anyMatch(warp -> warp.equalsIgnoreCase(name));
    }

    private void load() {
        if (file.length() != 0) {
            for (String warp : cfg.getConfigurationSection("WARPS").getKeys(false)) {
                warps.add(warp);
            }
        }
    }

    public List<String> getWarps() {
        return warps;
    }

    private void save() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}