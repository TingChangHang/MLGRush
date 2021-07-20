package com.locydragon.mlgrush.listeners;

import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.core.data.InGame;
import com.locydragon.mlgrush.mcxiafeng.SettingManager;

import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author LocyDragon
 * @version V1.0
 * 玩家在游戏过程中退出游戏检测
 */

public class QuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (InGame.isPlaying(e.getPlayer())) {
            InGame.quit(e.getPlayer());
            Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), SettingManager.lineformessage$ExitGame));
        }
    }

    @EventHandler
    public void onQuit(PlayerKickEvent e) {
        if (InGame.isPlaying(e.getPlayer())) {
            InGame.quit(e.getPlayer());
            Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), SettingManager.lineformessage$BeKickGame));
        }
    }
}
