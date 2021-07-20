package com.locydragon.mlgrush.listeners;

import com.locydragon.mlgrush.core.GameArea;
import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.core.data.InGame;
import com.locydragon.mlgrush.mcxiafeng.SettingManager;

import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * @author LocyDragon
 * @version V1.0
 * 当玩家超出区域范围，自动拉回
 */

public class OutOfArea implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (InGame.isPlaying(e.getPlayer())) {
            if (e.getPlayer().getLocation().getWorld().getName().equals(RushInstances.RUSH_LOBBY.getWorld().getName())) {
                InGame.quit(e.getPlayer());
                Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), SettingManager.lineformessage$ExitGame));
                return;
            }
            if (((int) e.getFrom().getX() != (int) e.getTo().getX()) || ((int) e.getFrom().getY() != (int) e.getTo().getY())
                    || ((int) e.getFrom().getZ() != (int) e.getTo().getZ())) {
                GameArea area = InGame.get(e.getPlayer()).area;
//                if(e.getPlayer().getLocation().getY() < 0) {
//                	
//                }
                if (!area.isInAreaForY(e.getTo())) {
                    if (e.getPlayer().getName().equals(area.A.getName())) {
                        e.getPlayer().teleport(area.SPAWN_A);
                    } else if (e.getPlayer().getName().equals(area.B.getName())) {
                        e.getPlayer().teleport(area.SPAWN_B);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player)e.getEntity();
            if (InGame.isPlaying(p)) {
                e.setDamage(0);
            }
        }
    }
}
