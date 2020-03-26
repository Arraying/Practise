package de.arraying.practise;

import de.arraying.practise.kit.Kit;
import de.arraying.practise.rank.Rank;
import de.arraying.practise.sql.SQLCache;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.text.DecimalFormat;
import java.util.Arrays;

import static de.arraying.practise.Practise.getPractise;

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
public final class PractiseListener implements Listener {

    private int damageThresholdCache;

    /**
     * When the weather changes.
     * @param event The event.
     */
    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    /**
     * When a player logs into the server.
     * @param event The event.
     */
    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        getPractise().getRankHandler().getPermissions().addPermissions(event.getPlayer());
    }

    /**
     * When a player joins the server.
     * @param event The event.
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.GRAY + "[+] " + getRankColour(player) + player.getName());
        player.setOp(false);
        player.teleport(getPractise().getSpawnHandler().getLocation());
        getPractise().getItemHandler().addItems(player);
        for(String line : getPractise().getConfig().getStringList("welcome")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
        }
    }

    /**
     * When a player leaves the server.
     * @param event The event.
     */
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.GRAY + "[-] " + getRankColour(player) + player.getName());
    }

    /**
     * When a player gets kicked from the server.
     * @param event The event.
     */
    @EventHandler
    public void onKick(PlayerKickEvent event) {
        if(event.getReason().equals("disconnect.spam")) {
            event.setCancelled(true);
        }
    }

    /**
     * When a command is pre processed.
     * @param event The event.
     */
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        int index = event.getMessage().indexOf(" ");
        String command = event.getMessage().substring(0, index == -1 ? event.getMessage().length() : index).toLowerCase();
        if((getPractise().getConfig().getStringList("blocked.list").contains(command)
                || command.contains(":"))
                && !event.getPlayer().isOp()) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', getPractise().getConfig().getString("blocked.message", "")));
        }
    }

    /**
     * When a player talks in chat.
     * @param event The event.
     */
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        //event.setFormat(getRankColour(event.getPlayer()) + "%s" + ChatColor.WHITE + ": " + ChatColor.GRAY + "%s");
        Player player = event.getPlayer();
        Rank rank = getPractise().getRankHandler().getRank(player.getUniqueId());
        ComponentBuilder rankInfoBuilder = new ComponentBuilder("This player has the ")
                .color(ChatColor.GRAY)
                .append(rank.getMeta().getDisplayName())
                .color(rank.getMeta().getColour())
                .append(" rank.\n\n")
                .color(ChatColor.GRAY);
        Arrays.stream(rank.getMeta().getDescription())
                .forEach(it -> rankInfoBuilder.append(it + "\n").color(ChatColor.GRAY));
        BaseComponent[] rankInfo = rankInfoBuilder.create();
        BaseComponent[] baseComponents = new ComponentBuilder(player.getName())
                .color(rank.getMeta().getColour())
                .event(new HoverEvent(
                        HoverEvent.Action.SHOW_TEXT,
                        rankInfo
                ))
                .append(": ")
                .color(ChatColor.WHITE)
                .append(event.getMessage())
                .color(ChatColor.GRAY)
                .create();
        for(Player online : Bukkit.getOnlinePlayers()) {
            online.spigot().sendMessage(baseComponents);
        }
        event.setCancelled(true);
    }

    /**
     * When a player drops an item.
     * @param event The event.
     */
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if(event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }

    /**
     * When a player picks up an item.
     * @param event The event.
     */
    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        if(event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }

    /**
     * When a player gets damaged.
     * @param event The event.
     */
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(event.getEntityType() != EntityType.PLAYER) {
            return;
        }
        if(event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK
                && event.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) {
            event.setCancelled(true);
        }
    }

    /**
     * When a player gets damaged by another player.
     * @param event The event.
     */
    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if(event.getEntity().getType() != EntityType.PLAYER
                || event.getDamager().getType() != EntityType.PLAYER) {
            return;
        }
        if(((Player) event.getEntity()).isFlying()
                || ((Player) event.getDamager()).isFlying()) {
            event.setCancelled(true);
        }
        if(isNotDamageAllowed(event.getDamager(), event.getEntity())) {
            event.setCancelled(true);
        }
    }

    /**
     * When the player is hungry.
     * @param event The event.
     */
    @EventHandler
    public void onHungry(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    /**
     * When a player dies.
     * @param event The event.
     */
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if(player.getKiller() != null) {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            Player killer = player.getKiller();
            player.sendMessage(getRankColour(killer) + killer.getName() + ChatColor.GRAY + " had " + decimalFormat.format(killer.getHealth()) + " health left.");
            killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 2));
            if(SQLCache.INSTANCE.get(killer.getUniqueId()).getKit().equals(Kit.ARCHER.name())) {
                ItemStack arrows = new ItemStack(Material.ARROW);
                arrows.setAmount(5);
                killer.getInventory().addItem(arrows);
            }
            getPractise().getStreakHandler().onPlayerKill(killer);
            getPractise().getStreakHandler().onPlayerDeath(player);
            int streak = getPractise().getStreakHandler().getStreak(killer);
            if(streak >= 5
                    && streak % 5  == 0) {
                Bukkit.broadcastMessage(getRankColour(killer) + killer.getName() + ChatColor.GRAY + " got a " + streak + " killstreak.");
            }
        }
        event.setDeathMessage(null);
        event.getDrops().clear();
        event.setDroppedExp(0);
    }

    /**
     * When a player respawns.
     * @param event The event.
     */
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(getPractise().getSpawnHandler().getLocation());
        getPractise().getItemHandler().addItems(event.getPlayer());
    }

    /**
     * When the player places a block.
     * @param event The event.
     */
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }

    /**
     * When the player breaks a block.
     * @param event The event.
     */
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }

    /**
     * When a projectile lands.
     * @param event The event.
     */
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        event.getEntity().remove();
    }

    /**
     * Gets the rank colour for the player.
     * @param player The player.
     * @return The rank colour.
     */
    private ChatColor getRankColour(Player player) {
        return getPractise().getRankHandler().getRank(player.getUniqueId()).getMeta().getColour();
    }

    /**
     * Whether or not damage is allowed between two entities.
     * @param one The first entity.
     * @param another The second entity.
     * @return True if it is, false otherwise.
     */
    private boolean isNotDamageAllowed(Entity one, Entity another) {
        return one.getLocation().getY() >= getDamageThreshold()
                || another.getLocation().getY() >= getDamageThreshold();
    }

    /**
     * Gets the damage threshold.
     * @return The damage threshold.
     */
    private int getDamageThreshold() {
        if(damageThresholdCache <= 0) {
            damageThresholdCache = getPractise().getConfig().getInt("damage", 65);
        }
        return damageThresholdCache;
    }

}
