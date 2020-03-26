package de.arraying.practise.kit;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
public abstract class KitItems {

    /**
     * Gets the display icon.
     * @return The display icon.
     */
    public abstract ItemStack getIcon();

    /**
     * Gets the helmet.
     * @return The helmet.
     */
    public abstract ItemStack getHelmet();

    /**
     * The chestplate.
     * @return The chestplate.
     */
    public abstract ItemStack getChestplate();

    /**
     * Gets the leggings.
     * @return The leggings.
     */
    public abstract ItemStack getLeggings();

    /**
     * Gets the boots.
     * @return The boots.
     */
    public abstract ItemStack getBoots();

    /**
     * Gets the items.
     * @return The items.
     */
    public abstract List<ItemStack> getItems();

    /**
     * Gets an unbreakable itemstack.
     * @param material The material.
     * @return The item.
     */
    protected ItemStack of(Material material) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.spigot().setUnbreakable(true);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

}
