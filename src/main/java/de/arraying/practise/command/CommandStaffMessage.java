package de.arraying.practise.command;

import de.arraying.nexus.command.NexusCommandContext;
import de.arraying.practise.Practise;
import de.arraying.practise.command.abstraction.AbstractCommand;
import de.arraying.practise.rank.Rank;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;

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
public final class CommandStaffMessage extends AbstractCommand {

    private static Player LAST;

    /**
     * Creates the command.
     */
    public CommandStaffMessage() {
        super("staffmessage", Rank.MOD, Target.PLAYER, "staffmsg", "smsg", "sm");
    }

    /**
     * Sets the last player to have used the command.
     * @param player The player.
     */
    static void setLast(Player player) {
        LAST = player;
    }

    /**
     * Messages someone.
     * @param context The context.
     */
    @Override
    public void execute(NexusCommandContext context) {
        if(context.getArgs().length == 0) {
            context.reply("Please provide the person to message.");
            return;
        }
        if(context.getArgs().length == 1) {
            context.reply("Please provide a message.");
            return;
        }
        Player target = context.getArgs()[0].equals("!") ? LAST : Bukkit.getPlayer(context.getArgs()[0]);
        if(target == null) {
            context.reply("That player is not online.");
            return;
        }
        if(Practise.getPractise().getRankHandler().getRank(target.getUniqueId()).isAtLeast(Rank.MOD)) {
            context.reply("You cannot message staff members.");
            return;
        }
        String message = String.join(" ", Arrays.copyOfRange(context.getArgs(), 1, context.getArgs().length));
        String toSend = CommandStaff.format((Player) context.getSender(), ChatColor.DARK_RED, ChatColor.RED, message);
        Practise.getPractise().getRankHandler().sendToStaff(toSend);
        target.sendMessage(toSend);
    }

}
