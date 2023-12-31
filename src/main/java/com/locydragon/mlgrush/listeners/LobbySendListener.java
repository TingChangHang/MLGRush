package com.locydragon.mlgrush.listeners;

import com.locydragon.mlgrush.MLGRush;
import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.mcxiafeng.SettingManager;

import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author LocyDragon
 * @version V1.0
 * 登入传送类
 */

public class LobbySendListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), SettingManager.lineforCore$PlayerJoinNotice));
        Bukkit.getScheduler().runTaskLater(MLGRush.instance, () -> {
            if (RushInstances.RUSH_LOBBY != null) {
                e.getPlayer().teleport(RushInstances.RUSH_LOBBY);
            }
            e.getPlayer().getInventory().clear();
            e.getPlayer().getInventory().setItem(0, RushInstances.MATCHING_ITEM);
            e.getPlayer().getInventory().setItem(4, RushInstances.CHANGEINV_ITEM);
            e.getPlayer().getInventory().setItem(8, RushInstances.WATCHER_ITEM);
            e.getPlayer().updateInventory();
            e.getPlayer().sendMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), SettingManager.lineforCore$PlayerJoinMsg));
        },1);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), SettingManager.lineforCore$PlayerLeaveNotice));
    }

    @EventHandler
    public void onLeave(PlayerKickEvent e) {
        e.setLeaveMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), SettingManager.lineforCore$PlayerLeaveNotice));
    }
}
