package de.devcyntrix.bukkit;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.bukkit.Location;

public class BukkitSerialization {

    public static SimpleModule bukkit() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Location.class, new LocationSerializer());
        module.addDeserializer(Location.class, new LocationDeserializer());
        return module;
    }

}
