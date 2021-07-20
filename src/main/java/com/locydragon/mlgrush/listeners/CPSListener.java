package com.locydragon.mlgrush.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LocyDragon
 * @version V1.0
 * CPS检测类
 */

public class CPSListener implements Listener {
    public Map<String, Integer> cpsLeft = new HashMap();
    public Map<String, Integer> cpsRight = new HashMap();

    /**
     * 清除cps数据
     */

    public void clearCps() {
        this.cpsRight.clear();
        this.cpsLeft.clear();
    }

    /**
     * 获取cps数据
     * @param playeruuid 玩家名称
     * @return cps
     */

    public int getLeft(String playeruuid) {
        int cps = this.cpsLeft.getOrDefault(playeruuid.toLowerCase(), -1);
        if (cps == -1) {
            return 0;
        }
        return cps;
    }

    /**
     * 获取cps数据
     * @param playeruuid 玩家名称
     * @return cps
     */

    public int getRight(String playeruuid) {
        int cps = this.cpsRight.getOrDefault(playeruuid.toLowerCase(), -1);
        if (cps == -1) {
            return 0;
        }
        return cps;
    }

    /**
     * 放入cps数据
     * @param playeruuid 玩家名称
     */

    public void putLeft(String playeruuid) {
        if (cpsLeft.getOrDefault(playeruuid, -1) == -1) {
            cpsLeft.put(playeruuid, 1);
        } else {
            cpsLeft.put(playeruuid, this.cpsLeft.get(playeruuid) + 1);
        }
    }

    /**
     * 放入cps数据
     * @param playeruuid 玩家名称
     */

    public void putRight(String playeruuid) {
        if (cpsRight.getOrDefault(playeruuid, -1) == -1) {
            cpsRight.put(playeruuid, 1);
        } else {
            cpsRight.put(playeruuid, this.cpsRight.get(playeruuid) + 1);
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Action ac = e.getAction();

        if ((ac == Action.LEFT_CLICK_AIR) || (ac == Action.LEFT_CLICK_BLOCK)) {
            putLeft(e.getPlayer().getUniqueId().toString());
        } else if (((ac == Action.RIGHT_CLICK_AIR) || (ac == Action.RIGHT_CLICK_BLOCK))) {
            putRight(e.getPlayer().getUniqueId().toString());
        }
    }
}
