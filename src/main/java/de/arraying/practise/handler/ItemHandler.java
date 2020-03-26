package de.arraying.practise.handler;

import de.arraying.practise.kit.Kit;
import de.arraying.practise.kit.KitItems;
import de.arraying.practise.sql.SQLCache;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

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
public final class ItemHandler {

    /**
     * Adds items to the player.
     * @param player The player.
     */
    public void addItems(Player player) {
        PlayerInventory inventory = player.getInventory();
        inventory.clear();
        Kit kit;
        try {
            kit = Kit.valueOf(SQLCache.INSTANCE.get(player.getUniqueId()).getKit().toUpperCase());
        } catch(IllegalArgumentException exception) {
            exception.printStackTrace();
            kit = Kit.SOLDIER;
        }
        KitItems items = kit.getItems();
        inventory.setHelmet(items.getHelmet());
        inventory.setChestplate(items.getChestplate());
        inventory.setLeggings(items.getLeggings());
        inventory.setBoots(items.getBoots());
        for(ItemStack item : items.getItems()) {
            inventory.addItem(item);
        }
    }

}
