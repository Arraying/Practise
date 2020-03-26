package de.arraying.practise.kit;

import de.arraying.practise.kit.kits.KitArcher;
import de.arraying.practise.kit.kits.KitSoldier;

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
public enum Kit {

    /**
     * Soldier kit.
     */
    SOLDIER(new KitSoldier()),

    /**
     * Archer kit.
     */
    ARCHER(new KitArcher());

    private final KitItems items;

    /**
     * Creates a new kit.
     * @param items The items.
     */
    Kit(KitItems items) {
        this.items = items;
    }

    /**
     * Gets the kit items.
     * @return The items.
     */
    public KitItems getItems() {
        return items;
    }

}
