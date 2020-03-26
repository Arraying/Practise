package de.arraying.practise.kit.gui;

import de.arraying.nexus.gui.GUI;
import de.arraying.practise.kit.Kit;
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
public final class KitGUI extends GUI {

    /**
     * Creates a new GUI.
     */
    public KitGUI() {
        super(ChatColor.WHITE + "" + ChatColor.BOLD + "Kits", 9);
    }

    /**
     * Populates the GUI.
     */
    @Override
    public void populate() {
        for(int i = 0; i < Kit.values().length; i++) {
            Kit kit = Kit.values()[i];
            registerSlot(new KitSlot(i, kit));
        }
    }

}
