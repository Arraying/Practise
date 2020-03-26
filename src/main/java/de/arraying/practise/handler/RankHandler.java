package de.arraying.practise.handler;

import de.arraying.practise.rank.Rank;
import de.arraying.practise.rank.RankPermissions;
import de.arraying.practise.sql.SQLCache;
import org.bukkit.Bukkit;

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
public final class RankHandler {

    private final RankPermissions permissions = new RankPermissions();

    /**
     * Gets a rank for a player.
     * @param player The player UUID.
     * @return The rank.
     */
    public Rank getRank(UUID player) {
        Rank rank = Rank.of(SQLCache.INSTANCE.get(player).getRank());
        return rank == null ? Rank.DEFAULT : rank;
    }

    /**
     * Sends the message to all staff.
     * @param message The message.
     */
    public void sendToStaff(String message) {
        Bukkit.getOnlinePlayers().stream()
                .filter(it -> getRank(it.getUniqueId()).isAtLeast(Rank.MOD))
                .forEach(it -> it.sendMessage(message));
    }

    /**
     * Gets the rank permissions.
     * @return The permissions.
     */
    public RankPermissions getPermissions() {
        return permissions;
    }

}
