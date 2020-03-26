package de.arraying.practise.rank;

import de.arraying.practise.Practise;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

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
public final class RankPermissions {

    private final Map<UUID, PermissionAttachment> attachments = new HashMap<>();

    /**
     * Adds the permissions for the player.
     * @param player The player.
     */
    public void addPermissions(Player player) {
        PermissionAttachment attachment = player.addAttachment(Practise.getPractise());
        Rank rank = Practise.getPractise().getRankHandler().getRank(player.getUniqueId());
        if(rank == null) {
            rank = Rank.DEFAULT;
        }
        for(String permission : rank.getPermissions()) {
            attachment.setPermission(permission, true);
        }
        attachments.put(player.getUniqueId(), attachment);
    }

    /**
     * Removes the player's permissions.
     * @param player The player.
     */
    public void removePermissions(Player player) {
        attachments.get(player.getUniqueId()).remove();
    }

}
