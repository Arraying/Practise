package de.arraying.practise;

import de.arraying.nexus.Nexus;
import de.arraying.nexus.NexusConfiguration;
import de.arraying.nexus.sql.SQLConfiguration;
import de.arraying.nexus.sql.table.SQLColumn;
import de.arraying.nexus.sql.table.SQLTable;
import de.arraying.practise.command.*;
import de.arraying.practise.handler.*;
import de.arraying.practise.sql.SQLCache;
import de.arraying.practise.sql.SQLPlayer;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

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
public final class Practise extends Nexus {

    private static Practise practise;

    private final RankHandler rankHandler = new RankHandler();
    private final StreakHandler streakHandler = new StreakHandler();
    private final ItemHandler itemHandler = new ItemHandler();
    private final SpawnHandler spawnHandler = new SpawnHandler();
    private final AnnouncementHandler announcementHandler = new AnnouncementHandler();

    /**
     * Gets the practise plugin instance.
     * @return The plugin instance.
     */
    public static Practise getPractise() {
        return practise;
    }

    /**
     * Configures Nexus.
     * @param configuration The nexus config.
     */
    @Override
    public void configure(NexusConfiguration configuration) {
        SQLConfiguration sqlConfiguration = new SQLConfiguration();
        sqlConfiguration.getTables()
                .add(new SQLTable("pr_users", SQLPlayer.class)
                        .column(new SQLColumn(
                                "uuid",
                                SQLColumn.Type.STRING,
                                36,
                                true,
                                true
                        ))
                        .column(new SQLColumn(
                                "rank",
                                SQLColumn.Type.STRING,
                                null,
                                true,
                                false
                        ))
                        .column(new SQLColumn(
                                "kit",
                                SQLColumn.Type.STRING,
                                null,
                                true,
                                false
                        ))
                        .column(new SQLColumn(
                                "kills",
                                SQLColumn.Type.INT,
                                null,
                                true,
                                false
                        ))
                        .column(new SQLColumn(
                                "deaths",
                                SQLColumn.Type.INT,
                                null,
                                true,
                                false
                        ))
                );
        configuration.setSqlConfiguration(sqlConfiguration);
        configuration.getCommands().add(new CommandAlert());
        configuration.getCommands().add(new CommandFly());
        configuration.getCommands().add(new CommandInfo());
        configuration.getCommands().add(new CommandKit());
        configuration.getCommands().add(new CommandOP());
        configuration.getCommands().add(new CommandRules());
        configuration.getCommands().add(new CommandSave());
        configuration.getCommands().add(new CommandSetRank());
        configuration.getCommands().add(new CommandStaff());
        configuration.getCommands().add(new CommandStaffMessage());
        configuration.getCommands().add(new CommandStaffReply());
        configuration.getCommands().add(new CommandTeleport());
        configuration.getListeners().add(new PractiseListener());
    }

    /**
     * When the server starts up.
     */
    @Override
    public void onStartup() {
        practise = this;
        saveDefaultConfig();
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            PlaceholderAPI.registerPlaceholderHook("practise", new PractisePlaceholders());
        }
        announcementHandler.load(getConfig().getStringList("announcements"));
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, SQLCache.INSTANCE::saveAll, 12000, 12000);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this,
                () -> Bukkit.broadcastMessage(ChatColor.GREEN + "[TIP] " + announcementHandler.next()),
                6000,
                6000
        );
    }

    /**
     * When the server shuts down.
     */
    @Override
    public void onShutdown() {
        Bukkit.getScheduler().cancelTasks(this);
        SQLCache.INSTANCE.saveAll();
    }

    /**
     * Gets the rank handler.
     * @return The rank handler.
     */
    public RankHandler getRankHandler() {
        return rankHandler;
    }

    /**
     * Gets the streak handler.
     * @return The streak handler.
     */
    public StreakHandler getStreakHandler() {
        return streakHandler;
    }

    /**
     * Gets the item handler.
     * @return The item handler.
     */
    ItemHandler getItemHandler() {
        return itemHandler;
    }

    /**
     * Gets the spawn handler.
     * @return The spawn handler.
     */
    SpawnHandler getSpawnHandler() {
        return spawnHandler;
    }

}
