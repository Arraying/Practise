package de.arraying.practise.command;

import de.arraying.nexus.command.NexusCommandContext;
import de.arraying.practise.Practise;
import de.arraying.practise.command.abstraction.AbstractCommand;
import de.arraying.practise.rank.Rank;
import de.arraying.practise.rank.RankPermissions;
import de.arraying.practise.sql.SQLCache;
import de.arraying.practise.sql.SQLPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

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
public final class CommandSetRank extends AbstractCommand {

    /**
     * Creates the command.
     */
    public CommandSetRank() {
        super("setrank", Rank.ADMIN, Target.CONSOLE);
    }

    /**
     * Sets the rank.
     * @param context The command context.
     */
    @Override
    public void execute(NexusCommandContext context) {
        if(context.getArgs().length < 2) {
            context.reply("Please provide a player name and rank");
            return;
        }
        Player player = Bukkit.getPlayer(context.getArgs()[0]);
        if(player == null) {
            context.reply("Invalid player.");
            return;
        }
        Rank rank = Rank.of(context.getArgs()[1]);
        if(rank == null) {
            context.reply("Invalid rank. Available: " + String.join(", ",
                    Arrays.stream(Rank.values())
                            .map(Enum::name)
                            .collect(Collectors.toList())));
            return;
        }
        SQLPlayer sqlPlayer = SQLCache.INSTANCE.get(player.getUniqueId());
        sqlPlayer.setRank(rank.name());
        sqlPlayer.save();
        RankPermissions permissions = Practise.getPractise().getRankHandler().getPermissions();
        permissions.removePermissions(player);
        permissions.addPermissions(player);
        context.reply("Rank updated.");
    }

}
