package de.takko.simple.modules.warp;

import de.takko.simple.modules.warp.util.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class WarpManager {

    private File file;
    private FileConfiguration cfg;

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
    }

    public void addWarp(Location location, String name) {
        cfg.set(name + ".world", location.getWorld().getName());
        cfg.set(name + ".x", location.getX());
        cfg.set(name + ".y", location.getY());
        cfg.set(name + ".z", location.getZ());
        cfg.set(name + ".yaw", location.getYaw());
        cfg.set(name + ".pitch", location.getPitch());

        WarpModule.getWarpList().add(new Warp(location, name));

        save();
    }

    public boolean exists(String name) {
        return cfg.get(name) != null;
    }

    public void removeWarp(String name) {
        cfg.set(name, null);

        WarpModule.getWarpList().remove(getWarp(name));

        save();
    }

    public Warp getWarp(String name) {
        for (Warp warp : WarpModule.getWarpList()) {
            if (warp.getName().equalsIgnoreCase(name)) {
                return warp;
            }
        }
        return null;
    }

    public void loadWarps() {
        for (String warp : cfg.getKeys(false)) {
            World world = Bukkit.getWorld(cfg.getString(warp + ".world"));
            Double  x = cfg.getDouble(warp + ".x"),
                    y = cfg.getDouble(warp + ".y"),
                    z = cfg.getDouble(warp + ".z");
            Float   yaw = Float.valueOf(cfg.getString(warp + ".yaw")),
                    pitch = Float.valueOf(cfg.getString(warp + ".pitch"));
            Location location = new Location(world, x, y, z, yaw, pitch);
            WarpModule.getWarpList().add(new Warp(location, warp));
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