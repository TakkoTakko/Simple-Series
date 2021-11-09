package de.takko.simple.modules.warp.api;

import de.takko.simple.modules.warp.WarpManager;
import org.bukkit.Location;

import java.util.List;

public class WarpsAPI {

    private WarpManager wm;

    public WarpsAPI(WarpManager wm) {
        this.wm = wm;
    }

    public List<String> warps() {
        return wm.getWarps();
    }
    public void addWarp(String name, Location location) {
        wm.addWarp(name, location);
    }
    public void removeWarp(String name) {
        wm.removeWarp(name);
    }
    public Location getWarp(String name) {
        return wm.getWarp(name);
    }
}