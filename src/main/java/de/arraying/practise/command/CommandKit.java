package de.arraying.practise.command;

import de.arraying.nexus.command.NexusCommandContext;
import de.arraying.nexus.gui.GUIManager;
import de.arraying.practise.command.abstraction.AbstractCommand;
import de.arraying.practise.kit.gui.KitGUI;
import de.arraying.practise.rank.Rank;
import org.bukkit.entity.Player;

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
public final class CommandKit extends AbstractCommand {

    /**
     * Creates the command.
     */
    public CommandKit() {
        super("kit", Rank.DEFAULT, Target.PLAYER, "k", "kits");
    }

    /**
     * Opens the kit GUI.
     * @param context The context.
     */
    @Override
    public void execute(NexusCommandContext context) {
        GUIManager.INSTANCE.openGUI((Player) context.getSender(), new KitGUI());
    }

}
