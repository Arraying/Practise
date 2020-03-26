package de.arraying.practise.command;

import de.arraying.nexus.command.NexusCommandContext;
import de.arraying.practise.Practise;
import de.arraying.practise.command.abstraction.AbstractCommand;
import de.arraying.practise.rank.Rank;
import de.arraying.practise.sql.SQLCache;
import de.arraying.practise.sql.SQLPlayer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.math.RoundingMode;
import java.text.DecimalFormat;

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
public final class CommandInfo extends AbstractCommand {

    /**
     * Creates the command.
     */
    public CommandInfo() {
        super("info", Rank.DEFAULT, Target.BOTH, "i", "statistics", "stats");
    }

    /**
     * Shows info on a user.
     * @param context The context.
     */
    @Override
    public void execute(NexusCommandContext context) {
        Player target;
        if(context.getArgs().length == 0) {
            if(context.getSender() instanceof Player) {
                target = (Player) context.getSender();
            } else {
                context.reply("You need to specify a player.");
                return;
            }
        } else {
            target = Bukkit.getPlayer(context.getArgs()[0]);
        }
        if(target == null) {
            context.reply("The player is invalid.");
            return;
        }
        Rank rank = Practise.getPractise().getRankHandler().getRank(target.getUniqueId());
        SQLPlayer sqlPlayer = SQLCache.INSTANCE.get(target.getUniqueId());
        double kills = sqlPlayer.getKills();
        double deaths = sqlPlayer.getDeaths();
        double kdr = deaths == 0 ? kills : kills / deaths;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        context.reply(rank.getMeta().getColour() + target.getName() + ChatColor.GRAY + " has the rank " + rank.getMeta().getDisplayName() + ", " +
                sqlPlayer.getKills() + " kills, " +
                sqlPlayer.getDeaths() + " deaths, " +
                decimalFormat.format(kdr) + " KDR, and is on a " +
                Practise.getPractise().getStreakHandler().getStreak(target) + " killstreak.");
    }

}
