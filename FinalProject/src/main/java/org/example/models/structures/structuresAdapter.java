package org.example.models.structures;

import com.google.gson.*;
import org.example.models.structures.*;

import java.lang.reflect.Type;

public class structuresAdapter implements JsonDeserializer<Structures> {
    @Override
    public Structures deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        switch (type) {
            case "Farm":
                return context.deserialize(json, Farm.class);
            case "Barrack":
                return context.deserialize(json, Barrack.class);
            case "Market":
                return context.deserialize(json, Market.class);
            case "Tower":
                return context.deserialize(json, Tower.class);
            default:
                throw new JsonParseException("Unknown type: " + type);
        }
    }
}

