package de.arraying.practise.sql;

import de.arraying.nexus.sql.entity.SQLEntity;
import de.arraying.practise.Practise;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
public enum SQLCache {

    /**
     * The SQL cache instance.
     */
    INSTANCE;

    private final Map<UUID, SQLPlayer> cache = new HashMap<>();

    /**
     * Loads the cache for the UUID.
     * @param uuid The UUID.
     */
    public void load(UUID uuid) {
        SQLPlayer player = new SQLPlayer(uuid);
        switch(player.load()) {
            case ERROR:
                Practise.getPractise().getLogger().severe("Could not load " + uuid);
                break;
            case NONE:
                player.create();
        }
        cache.put(uuid, player);
    }

    /**
     * Gets the SQL user for the UUID.
     * @param uuid The uuid.
     * @return The user.
     */
    public SQLPlayer get(UUID uuid) {
        if(!cache.containsKey(uuid)) {
            load(uuid);
        }
        return cache.get(uuid);
    }

    /**
     * Saves all.
     */
    public void saveAll() {
        cache.values().forEach(SQLEntity::save);
    }

}
