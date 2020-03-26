package de.arraying.practise.handler;

import de.arraying.practise.sql.SQLCache;
import org.bukkit.entity.Player;

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
public final class StreakHandler {

    private final Map<UUID, Integer> streaks = new HashMap<>();

    /**
     * Gets the kill streak for a player.
     * @param player The player.
     * @return The kill streak.
     */
    public int getStreak(Player player) {
        return streaks.getOrDefault(player.getUniqueId(), 0);
    }

    /**
     * When a player gets a kill.
     * @param player The player.
     */
    public void onPlayerKill(Player player) {
        UUID uuid = player.getUniqueId();
        if(!streaks.containsKey(uuid)) {
            streaks.put(uuid, 0);
        }
        streaks.put(player.getUniqueId(), streaks.get(player.getUniqueId()) + 1);
        SQLCache.INSTANCE.get(uuid).addKill();
    }

    /**
     * When a player dies.
     * @param player The player.
     */
    public void onPlayerDeath(Player player) {
        streaks.remove(player.getUniqueId());
        SQLCache.INSTANCE.get(player.getUniqueId()).addDeath();
    }

}
