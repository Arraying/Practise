package de.arraying.practise;

import de.arraying.practise.rank.Rank;
import de.arraying.practise.sql.SQLCache;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.entity.Player;

import java.util.UUID;

import static de.arraying.practise.Practise.getPractise;

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
public final class PractisePlaceholders extends PlaceholderHook {

    /**
     * When a placeholder is requested.
     * @param p The player.
     * @param params The parameter.
     * @return The placeholder.
     */
    @Override
    public String onPlaceholderRequest(Player p, String params) {
        UUID uuid = p.getUniqueId();
        switch(params.toLowerCase()) {
            case "rank":
                Rank rank = getPractise().getRankHandler().getRank(uuid);
                return rank.getMeta().getColour() + rank.getMeta().getDisplayName();
            case "kills":
                return String.valueOf(SQLCache.INSTANCE.get(uuid).getKills());
            case "deaths":
                return String.valueOf(SQLCache.INSTANCE.get(uuid).getDeaths());
            case "streak":
                return String.valueOf(getPractise().getStreakHandler().getStreak(p));
        }
        return null;
    }

}
