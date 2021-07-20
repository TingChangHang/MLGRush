package com.locydragon.mlgrush.handlers;

import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.core.data.GamePlayer;
import com.locydragon.mlgrush.core.data.InGame;
import com.locydragon.mlgrush.mcxiafeng.SettingManager;
import com.locydragon.mlgrush.utils.ActionBar;

import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author LocyDragon
 * @version V1.0
 * 自动公告发送类
 */

public class BroadCastHandler extends BukkitRunnable {
    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (InGame.isPlaying(p)) {
                GamePlayer player = InGame.gamePlayerHashMap.get(p.getUniqueId().toString());
                ActionBar.sendActionBar(p,PlaceholderAPI.setPlaceholders(p, SettingManager.lineformessage$BroadCast)
                        );
            } else {
                ActionBar.sendActionBar(p, RushInstances.BROAD_CAST);
            }
        }
    }
}
