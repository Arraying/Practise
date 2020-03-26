package de.arraying.practise.handler;

import de.arraying.practise.Practise;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Copyright 2018 Arraying
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public final class SpawnHandler {

    private static final String KEY_WORLD = "spawn.world";
    private static final String KEY_X = "spawn.x";
    private static final String KEY_Y = "spawn.y";
    private static final String KEY_Z = "spawn.z";
    private Location cache;

    /**
     * Gets the spawn location.
     * @return The location.
     */
    public Location getLocation() {
        if(cache == null) {
            FileConfiguration configuration = Practise.getPractise().getConfig();
            String worldName = configuration.getString(KEY_WORLD, "world");
            int x = configuration.getInt(KEY_X, 0);
            int y = configuration.getInt(KEY_Y, 65);
            int z = configuration.getInt(KEY_Z, 0);
            World world = Bukkit.getWorld(worldName);
            if(world == null) {
                world = Bukkit.getWorlds().get(0);
            }
            cache = new Location(world, x, y, z);
        }
        return cache;
    }

}
