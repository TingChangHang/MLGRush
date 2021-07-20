package com.locydragon.mlgrush.core.data;

import com.locydragon.mlgrush.MLGRush;
import com.locydragon.mlgrush.core.GameArea;
import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.listeners.AllowMove;
import com.locydragon.mlgrush.listeners.ChangeInvListener;
import com.locydragon.mlgrush.mcxiafeng.SettingManager;

import lombok.NonNull;
import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * @author LocyDragon
 * @version V1.0
 * 游戏中玩家控制类
 */
public class GamePlayer {
    public Player target;
    public GameArea area;
    public boolean info = true; //是否打印信息

    /**
     * GamePlayer构造函数
     * 不建议直接使用该构造函数
     * @param player 进入游戏的玩家
     * @param area 玩家所在的游戏区域
     */
    public GamePlayer(@NonNull Player player, @NonNull GameArea area) {
        target = player;
        this.area = area;
        AllowMove.addMove(player, false);
        player.sendMessage(PlaceholderAPI.setPlaceholders(player, PlaceholderAPI.setPlaceholders(player, SettingManager.lineformessage$StartTimer5)));
        player.getLocation().getWorld().playSound(player.getLocation(), Sound.NOTE_PIANO,1,1);
        Bukkit.getScheduler().runTaskLater(MLGRush.instance, () -> {
            if (player != null && info) {
                player.sendMessage(PlaceholderAPI.setPlaceholders(player, SettingManager.lineformessage$StartTimer4));
                player.getLocation().getWorld().playSound(player.getLocation(), Sound.NOTE_PIANO,1,1);
            } else {
                return;
            }
        }, 20);
        Bukkit.getScheduler().runTaskLater(MLGRush.instance, () -> {
            if (player != null && info) {
            	player.sendMessage(PlaceholderAPI.setPlaceholders(player, SettingManager.lineformessage$StartTimer3));
                player.getLocation().getWorld().playSound(player.getLocation(), Sound.NOTE_PIANO,1,1);
            } else {
                return;
            }
        }, 40);
        Bukkit.getScheduler().runTaskLater(MLGRush.instance, () -> {
            if (player != null && info) {
            	player.sendMessage(PlaceholderAPI.setPlaceholders(player, SettingManager.lineformessage$StartTimer2));
                player.getLocation().getWorld().playSound(player.getLocation(), Sound.NOTE_PIANO,1,1);
            } else {
                return;
            }
        }, 60);
        Bukkit.getScheduler().runTaskLater(MLGRush.instance, () -> {
            if (player != null && info) {
            	player.sendMessage(PlaceholderAPI.setPlaceholders(player, SettingManager.lineformessage$StartTimer1));
                player.getLocation().getWorld().playSound(player.getLocation(), Sound.NOTE_PIANO,1,1);
            } else {
                return;
            }
        }, 80);;
        Bukkit.getScheduler().runTaskLater(MLGRush.instance, () -> {
            if (player != null && info) {
                player.sendMessage(PlaceholderAPI.setPlaceholders(player, SettingManager.lineformessage$Start));
                player.getLocation().getWorld().playSound(player.getLocation(), Sound.LEVEL_UP,1,1);
                AllowMove.addMove(player, true);
                player.getInventory().setItem(ChangeInvListener.getPlayerInvConfig(player).getInt("STICKindex"), RushInstances.STICK);
                player.getInventory().setItem(ChangeInvListener.getPlayerInvConfig(player).getInt("GLASSindex"), RushInstances.GLASS);
                player.getInventory().setItem(ChangeInvListener.getPlayerInvConfig(player).getInt("AXEindex"), RushInstances.AXE);
                player.updateInventory();
            }
        }, 100);
    }
}
