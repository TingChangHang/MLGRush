package com.locydragon.mlgrush.core.team;

import com.locydragon.mlgrush.core.AreaLoader;
import com.locydragon.mlgrush.core.GameArea;
import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.core.data.GamePlayer;
import com.locydragon.mlgrush.core.data.InGame;
import com.locydragon.mlgrush.mcxiafeng.SettingManager;

import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author LocyDragon
 * @version V1.0
 * 自动匹配机制
 */

public class MatchingSystem extends BukkitRunnable {
    Random random = new Random();

    @Override
    public void run() {
        if (TeamMatching.matchingPlayer.size() >= 2) {
            Player targetA = Bukkit.getPlayer(TeamMatching.matchingPlayer
                    .get(random.nextInt(TeamMatching.matchingPlayer.size())));
            TeamMatching.removeWaiting(targetA);
            Player targetB = Bukkit.getPlayer(TeamMatching.matchingPlayer
                    .get(random.nextInt(TeamMatching.matchingPlayer.size())));
            TeamMatching.removeWaiting(targetB);
            if (targetA != null && targetB != null &&
                    !(targetA.getName().equals(targetB.getName()))) {
                List<String> okAreas = new ArrayList<>();
                for (String obj : AreaLoader.gameLauncher.keySet()) {
                    if (!AreaLoader.gameLauncher.get(obj).isPlaying) {
                        okAreas.add(obj);
                    }
                }
                if (okAreas.size() == 0) {
                    return;
                }
                GameArea randomArea =
                        AreaLoader.gameLauncher.get(okAreas.get(random.nextInt(okAreas.size())));
                randomArea.A = targetA;
                randomArea.B = targetB;
                targetA.teleport(randomArea.SPAWN_A);
                targetB.teleport(randomArea.SPAWN_B);
                randomArea.isPlaying = true;
                InGame.play(targetA, randomArea);
                InGame.play(targetB, randomArea);
                targetA.getInventory().clear();
                targetA.updateInventory();
                targetB.getInventory().clear();
                targetB.updateInventory();
                Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(targetA, SettingManager.lineformessage$NoticeStart));
                targetA.setCustomName(PlaceholderAPI.setPlaceholders(targetA,SettingManager.lineforTeamName$TeamNameA));
                targetB.setCustomName(PlaceholderAPI.setPlaceholders(targetB,SettingManager.lineforTeamName$TeamNameB));
            }
        }
    }
}
