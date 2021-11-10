package de.devcyntrix.bukkit;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.IOException;

public class LocationDeserializer extends StdDeserializer<Location> {

    public LocationDeserializer() {
        this(null);
    }

    public LocationDeserializer(Class<Location> t) {
        super(t);
    }

    @Override
    public Location deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String worldName = node.get("world").textValue();
        World world = Bukkit.getWorld(worldName);
        if (world == null)
            throw new IllegalArgumentException("world not found");

        double x = node.get("x").asDouble();
        double y = node.get("y").asDouble();
        double z = node.get("z").asDouble();
        float yaw = (float) node.get("yaw").asDouble();
        float pitch = (float) node.get("pitch").asDouble();

        return new Location(world, x, y, z, yaw, pitch);
    }

}
