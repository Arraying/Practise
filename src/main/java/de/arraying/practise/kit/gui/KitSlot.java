package de.arraying.practise.kit.gui;

import de.arraying.nexus.gui.slot.GUISlot;
import de.arraying.practise.kit.Kit;
import de.arraying.practise.sql.SQLCache;
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
final class KitSlot extends GUISlot {

    /**
     * Creates a new kit slot.
     * @param slot The slot.
     * @param kit The kit.
     */
    KitSlot(int slot, Kit kit) {
        super(slot,
                kit.getItems().getIcon(),
                player -> {
                    SQLCache.INSTANCE.get(player.getUniqueId()).setKit(kit.name());
                    player.sendMessage(ChatColor.GRAY + "The kit will be applied next time you respawn.");
                    player.closeInventory();
                }
        );
    }

}
