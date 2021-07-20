package com.locydragon.mlgrush.core.team;

import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.mcxiafeng.SettingManager;

import lombok.NonNull;
import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LocyDragon
 * @version V1.0
 * 自动匹配机制
 */

public class TeamMatching {
    public static List<String> matchingPlayer = new ArrayList<>();

    /**
     * 使某个玩家进入匹配
     * @param user 玩家对象
     */

    public static void addWaiting(@NonNull Player user) {
        matchingPlayer.add(user.getName());
        for (String obj : matchingPlayer) {
            if (Bukkit.getPlayer(obj) != null) {
                Bukkit.getPlayer(obj).sendMessage(PlaceholderAPI.setPlaceholders(user, SettingManager.lineformessage$WaitingJoin));
            }
        }
    }

    /**
     * 使某个玩家退出匹配
     * @param user 玩家对象
     */

    public static void removeWaiting(@NonNull Player user) {
        if (matchingPlayer.contains(user.getName())) {
            matchingPlayer.remove(user.getName());
        }
    }
}
