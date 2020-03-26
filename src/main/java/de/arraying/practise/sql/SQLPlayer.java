package de.arraying.practise.sql;

import de.arraying.nexus.sql.entity.SQLEntity;
import de.arraying.nexus.sql.entity.SQLEntityField;
import de.arraying.practise.Practise;
import de.arraying.practise.kit.Kit;
import de.arraying.practise.rank.Rank;

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

public final class SQLPlayer extends SQLEntity {

    @SQLEntityField(column = "rank") private String rank = Rank.DEFAULT.name();
    @SQLEntityField(column = "kit") private String kit = Kit.SOLDIER.name();
    @SQLEntityField(column = "kills") private int kills;
    @SQLEntityField(column = "deaths") private int deaths;

    /**
     * Creates a new SQL player.
     * @param uuid The UUID.
     */
    SQLPlayer(UUID uuid) {
        super(Practise.getPractise(), uuid.toString(), "uuid");
    }

    /**
     * Gets the rank.
     * @return The rank.
     */
    public String getRank() {
        return rank;
    }

    /**
     * Gets the kit.
     * @return The kit.
     */
    public String getKit() {
        return kit;
    }

    /**
     * Gets the kills.
     * @return The kills.
     */
    public int getKills() {
        return kills;
    }

    /**
     * Gets the deaths.
     * @return The deaths.
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * Sets the rank.
     * @param rank The rank.
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * Sets the kit.
     * @param kit The kit,
     */
    public void setKit(String kit) {
        this.kit = kit;
    }

    /**
     * Adds a kill.
     */
    public void addKill() {
        this.kills += 1;
    }

    /**
     * Adds a death.
     */
    public void addDeath() {
        this.deaths += 1;
    }

}
