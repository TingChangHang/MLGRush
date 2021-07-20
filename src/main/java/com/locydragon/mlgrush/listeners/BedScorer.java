package com.locydragon.mlgrush.listeners;

import com.locydragon.mlgrush.core.BedBlock;
import com.locydragon.mlgrush.core.data.GamePlayer;
import com.locydragon.mlgrush.core.data.InGame;
import com.locydragon.mlgrush.mcxiafeng.SettingManager;
import com.locydragon.mlgrush.utils.LocationUtil;

import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * @author LocyDragon
 * @version V1.0
 * 挖掘床以获得分数的控制类
 */

public class BedScorer implements Listener {

    @EventHandler(priority = EventPriority.MONITOR,ignoreCancelled = true)
    public void onBreak(BlockBreakEvent e) {
        if (InGame.isPlaying(e.getPlayer()) &&
                (e.getBlock().getType() == Material.BED
                        || e.getBlock().getType() == Material.BED_BLOCK)) {
            e.setCancelled(true);
            GamePlayer player = InGame.get(e.getPlayer());
            BedBlock bedBlock_A = player.area.bedBlock_A;
            BedBlock bedBlock_B = player.area.bedBlock_B;
            if(!isOwnBed(player,e.getBlock().getLocation())) {
            	if (LocationUtil.equals(bedBlock_A.head, e.getBlock().getLocation()) ||
                        LocationUtil.equals(bedBlock_A.buttom, e.getBlock().getLocation())) {
                    player.area.re();
                    int points = player.area.b_Score;
                    points++;
                    player.area.b_Score = points;
                    if (points >= 10) {
                    	Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders((Player) player, SettingManager.lineformessage$WinB));
                        InGame.quit(e.getPlayer());
                    }
                    return;
                }
                if (LocationUtil.equals(bedBlock_B.head, e.getBlock().getLocation()) ||
                        LocationUtil.equals(bedBlock_B.buttom, e.getBlock().getLocation())) {
                    player.area.re();
                    int points = player.area.a_Score;
                    points++;
                    player.area.a_Score = points;
                    if (points >= 10) {
                    	Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders((Player) player, SettingManager.lineformessage$WinA));
                        InGame.quit(e.getPlayer());
                    }
                    return;
                }
            }else {
            	e.getPlayer().sendMessage(PlaceholderAPI.setPlaceholders(e.getPlayer(), SettingManager.lineformessage$BreakSelfBed));
            }
            
        }
    }
    
    
    private boolean isOwnBed(GamePlayer gplayer, Location bedblockL) {
        BedBlock bedBlock_A = gplayer.area.bedBlock_A;
        BedBlock bedBlock_B = gplayer.area.bedBlock_B;
        if(gplayer.target == gplayer.area.A) {
        	if(bedBlock_A.buttom.equals(bedblockL)  || bedBlock_A.head.equals(bedblockL)) {
        		return true;
        	}
        }
        if(gplayer.target == gplayer.area.B) {
        	if(bedBlock_B.buttom.equals(bedblockL) || bedBlock_B.head.equals(bedblockL)) {
        		return true;
        	}
        }
		return false;
        
	}
}
