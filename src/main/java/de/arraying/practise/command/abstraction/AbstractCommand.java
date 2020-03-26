package de.arraying.practise.command.abstraction;

import de.arraying.nexus.command.NexusCommand;
import de.arraying.nexus.command.NexusCommandContext;
import de.arraying.practise.Practise;
import de.arraying.practise.rank.Rank;
import net.md_5.bungee.api.ChatColor;
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
public abstract class AbstractCommand extends NexusCommand {

    private final Target target;
    private final Rank perm;

    /**
     * Creates a new abstract command.
     * @param name The name of the command.
     * @param perm The rank permission.
     * @param target The target.
     * @param aliases The aliases.
     */
    public AbstractCommand(String name, Rank perm, Target target, String... aliases) {
        super(name, "", target, aliases);
        this.target = target;
        this.perm = perm;
    }

    /**
     * Executes the command.
     * @param context The context.
     */
    public abstract void execute(NexusCommandContext context);

    /**
     * When the command is executed.
     * @param context The context.
     */
    @Override
    public final void onCommand(NexusCommandContext context) {
        if(context.getSender() instanceof Player) {
            Rank rank = Practise.getPractise().getRankHandler().getRank(((Player) context.getSender()).getUniqueId());
            if(!rank.isAtLeast(perm)) {
                context.reply("You need to be " + perm.getMeta().getColour() + perm.getMeta().getDisplayName() + ChatColor.GRAY + " to do this.");
                return;
            }
        }
        execute(context);
    }

}
