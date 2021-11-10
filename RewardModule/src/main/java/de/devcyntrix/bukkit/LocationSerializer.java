package de.devcyntrix.bukkit;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.bukkit.Location;

import java.io.IOException;

public class LocationSerializer extends StdSerializer<Location> {

    public LocationSerializer() {
        this(null);
    }

    public LocationSerializer(Class<Location> t) {
        super(t);
    }

    @Override
    public void serialize(Location location, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("world", location.getWorld().getName());
        jsonGenerator.writeNumberField("x", location.getX());
        jsonGenerator.writeNumberField("y", location.getY());
        jsonGenerator.writeNumberField("z", location.getZ());
        jsonGenerator.writeNumberField("yaw", location.getYaw());
        jsonGenerator.writeNumberField("pitch", location.getPitch());
        jsonGenerator.writeEndObject();
    }
}
