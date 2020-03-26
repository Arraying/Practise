package de.arraying.practise.kit.kits;

import com.google.common.collect.Lists;
import de.arraying.nexus.item.ItemBuilder;
import de.arraying.practise.kit.KitItems;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
public final class KitArcher extends KitItems {

    /**
     * Gets the icon.
     * @return The icon.
     */
    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.BOW)
                .name(ChatColor.AQUA + "Archer")
                .build();
    }

    /**
     * @return Leather helmet.
     */
    @Override
    public ItemStack getHelmet() {
        return of(Material.LEATHER_HELMET);
    }

    /**
     * @return Leather chestplate.
     */
    @Override
    public ItemStack getChestplate() {
        return of(Material.LEATHER_CHESTPLATE);
    }

    /**
     * @return Leather leggings.
     */
    @Override
    public ItemStack getLeggings() {
        return of(Material.LEATHER_LEGGINGS);
    }

    /**
     * @return Leather boots.
     */
    @Override
    public ItemStack getBoots() {
        return of(Material.LEATHER_BOOTS);
    }

    /**
     * @return Wood sword, bow and arrows.
     */
    @Override
    public List<ItemStack> getItems() {
        ItemStack arrows = new ItemStack(Material.ARROW);
        arrows.setAmount(10);
        return Lists.newArrayList(
                of(Material.WOOD_SWORD),
                of(Material.BOW),
                arrows
        );
    }

}
