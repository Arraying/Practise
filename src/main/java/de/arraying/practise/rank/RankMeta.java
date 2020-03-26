package de.arraying.practise.rank;

import net.md_5.bungee.api.ChatColor;

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
public final class RankMeta {

    private final String displayName;
    private final ChatColor colour;
    private final String[] description;

    /**
     * Creates a new rank meta.
     * @param displayName The display name.
     * @param colour The colour.
     * @param description The description.
     */
    RankMeta(String displayName, ChatColor colour, String... description) {
        this.displayName = displayName;
        this.colour = colour;
        this.description = description;
    }

    /**
     * Gets the display name.
     * @return The display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the colour.
     * @return The colour.
     */
    public net.md_5.bungee.api.ChatColor getColour() {
        return colour;
    }

    /**
     * Gets the description.
     * @return The description.
     */
    public String[] getDescription() {
        return description;
    }

}
