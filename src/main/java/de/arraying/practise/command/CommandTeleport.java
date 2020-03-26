package de.arraying.practise.command;

import de.arraying.nexus.command.NexusCommandContext;
import de.arraying.practise.command.abstraction.AbstractCommand;
import de.arraying.practise.rank.Rank;
import org.bukkit.Bukkit;
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
public final class CommandTeleport extends AbstractCommand {

    /**
     * Creates the command.
     */
    public CommandTeleport() {
        super("teleport", Rank.MOD, Target.PLAYER, "tp", "tpa");
    }

    /**
     * Teleports the player to another player.
     * @param context The context.
     */
    @Override
    public void execute(NexusCommandContext context) {
        if(context.getArgs().length == 0) {
            context.reply("Please provide the name of the player to teleport to.");
            return;
        }
        Player player = (Player) context.getSender();
        Player target = Bukkit.getPlayer(context.getArgs()[0]);
        if(target == null) {
            context.reply("That player is not online.");
            return;
        }
        player.teleport(target);
        context.reply("You have been teleported.");
    }

}
