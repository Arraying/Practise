package de.arraying.practise.rank;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public enum Rank {

    /**
     * The default rank.
     */
    DEFAULT(
            new RankMeta(
                    "No Rank",
                    ChatColor.GRAY,
                    "No Rank is a rank given to all players",
                    "by default."
            ),
            "openboard.board.default",
            "openboard.nametag.default"
    ),

    /**
     * VIP donator.
     */
    VIP(
            new RankMeta(
                    "VIP",
                    ChatColor.WHITE,
                    "VIPs are players who have",
                    "donated money to the server."
            ),
            "openboard.nametag.vip"
    ),

    /**
     * A VIP+ Rank.
     */
    VIP_PLUS(
            new RankMeta(
                    "VIP+",
                    ChatColor.LIGHT_PURPLE,
                    "VIP+s are players who are",
                    "very special snowflakes."
            ),
            "openboard.nametag.vip+"
    ),

    /**
     * Low staff rank.
     */
    MOD(
            new RankMeta(
                    "Mod",
                    ChatColor.BLUE,
                    "Mods are in charge of helping",
                    "players, punishing those who",
                    "break the rules and handle reports."
            ),
            "openboard.nametag.mod"
    ),

    /**
     * High staff rank.
     */
    ADMIN(
            new RankMeta(
                    "Admin",
                    ChatColor.RED,
                    "Admins run the server.",
                    "They maintain the server, code",
                    "plugins and oversee the whole",
                    "staff team and playerbase."
            ),
            "openboard.nametag.admin",
            "practise.op"
    );

    private final RankMeta meta;
    private final String[] permissions;

    /**
     * Creates a new rank.
     * @param meta The rank meta.
     * @param permissions The permissions.
     */
    Rank(RankMeta meta, String... permissions) {
        this.meta = meta;
        this.permissions = permissions;
    }

    /**
     * Gets the rank via name.
     * @param name The name.
     * @return The rank, or null if it is invalid.
     */
    public static Rank of(String name) {
        try {
            return Rank.valueOf(name.toUpperCase());
        } catch(IllegalArgumentException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Whether or not this rank is at least as high as the other rank.
     * @param other The other rank.
     * @return True if it is, false otherwise.
     */
    public boolean isAtLeast(Rank other) {
        return this.ordinal() >= other.ordinal();
    }

    /**
     * Gets the rank meta.
     * @return The meta.
     */
    public RankMeta getMeta() {
        return meta;
    }

    /**
     * Gets the permissions.
     * @return The permissions.
     */
    public List<String> getPermissions() {
        List<String> permissions = new ArrayList<>();
        Rank[] ranks = Rank.values();
        for(int i = 0; i <= this.ordinal(); i++) {
            permissions.addAll(Arrays.asList(ranks[i].permissions));
        }
        return permissions;
    }

}
