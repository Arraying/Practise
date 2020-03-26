package de.arraying.practise.command;

import de.arraying.nexus.command.NexusCommandContext;
import de.arraying.practise.Practise;
import de.arraying.practise.command.abstraction.AbstractCommand;
import de.arraying.practise.handler.RankHandler;
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
public final class CommandStaff extends AbstractCommand {

    /**
     * Creates the command.
     */
    public CommandStaff() {
        super("staff", Rank.DEFAULT, Target.PLAYER, "s", "staffc", "sc");
    }

    /**
     * Formats a chat message.
     * @param player The player.
     * @param name The name colour.
     * @param text The text colour.
     * @param message The message.
     * @return The formatted message,
     */
    static String format(Player player, ChatColor name, ChatColor text, String message) {
        return name + player.getName() + ChatColor.WHITE + ": " + text + message;
    }

    /**
     * Sends a message to staff members.
     * @param context The context.
     */
    @Override
    public void execute(NexusCommandContext context) {
        if(context.getArgs().length == 0) {
            context.reply("Please provide a message.");
            return;
        }
        Player player = (Player) context.getSender();
        RankHandler rankHandler = Practise.getPractise().getRankHandler();
        String message = String.join(" ", context.getArgs());
        Rank rank = rankHandler.getRank(player.getUniqueId());
        boolean isStaff = rank.isAtLeast(Rank.MOD);
        String toSend = isStaff ?
                format(player, ChatColor.DARK_AQUA, ChatColor.AQUA, message) :
                format(player, ChatColor.DARK_RED, ChatColor.RED, message);
        rankHandler.sendToStaff(toSend);
        if(!isStaff) {
            player.sendMessage(toSend);
            CommandStaffMessage.setLast(player);
        }
    }

}
