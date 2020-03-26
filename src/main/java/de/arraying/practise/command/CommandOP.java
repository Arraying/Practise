package de.arraying.practise.command;

import de.arraying.nexus.command.NexusCommandContext;
import de.arraying.practise.command.abstraction.AbstractCommand;
import de.arraying.practise.rank.Rank;

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
public final class CommandOP extends AbstractCommand {

    /**
     * Creates the command.
     */
    public CommandOP() {
        super("op", Rank.ADMIN, Target.PLAYER, "opme");
    }

    /**
     * Ops the player.
     * @param context The command context.
     */
    @Override
    public void execute(NexusCommandContext context) {
        context.getSender().setOp(true);
        context.reply("You have been given OP. Do not abuse this.");
    }

}
