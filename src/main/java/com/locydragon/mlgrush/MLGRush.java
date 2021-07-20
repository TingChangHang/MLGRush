package com.locydragon.mlgrush;

import com.locydragon.mlgrush.commands.CpsHandler;
import com.locydragon.mlgrush.commands.RushCmdHandler;
import com.locydragon.mlgrush.core.AreaLoader;
import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.core.team.MatchingSystem;
import com.locydragon.mlgrush.handlers.BroadCastHandler;
import com.locydragon.mlgrush.handlers.ConfigLoader;
import com.locydragon.mlgrush.handlers.ListenerRegister;
import com.locydragon.mlgrush.listeners.CPSListener;
import com.locydragon.mlgrush.mcxiafeng.PaPiApi;
import com.locydragon.mlgrush.mcxiafeng.SettingManager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author LocyDragon
 * @version V1.0
 * 主类
 */

public class MLGRush extends JavaPlugin {
    public static Random random = new Random();
    public static CPSListener cps = new CPSListener();
    public static FileConfiguration rushConfig = null;
    public static MLGRush instance = null;
    public static String serverName = "MLGRush";
    public static String ip = "localhost:25565";

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getConsoleSender().sendMessage("▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
        Bukkit.getConsoleSender().sendMessage("§bMLGRUSH v E1.0 ");
        Bukkit.getConsoleSender().sendMessage("§e此插件由LocyDragon制作(https://www.mcbbs.net/?2149109)");
        Bukkit.getConsoleSender().sendMessage("§aMC_xiafeng_233魔改(https://space.bilibili.com/396930389)");
        Bukkit.getConsoleSender().sendMessage("§8§d夏枫：这个版本因为国内很少人人发出来，能找到的只有这个， L wklqn kh lv uhdoob d odcb grj dqg wkh Mdqxdub EXJ zdv vwloo wkhuh zkhq L grzqordghg lw lq Mxob ,BUG我基本都修了也加了很多东西，希望你喜欢我魔改的作品");
        Bukkit.getConsoleSender().sendMessage("§a插件遵循GPLv3协议进行开源  : https://gitee.com/glxiafeng233/mlgrush-v-e1.0");
        Bukkit.getConsoleSender().sendMessage("▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
        saveDefaultConfig();
        Bukkit.getPluginCommand("rush").setExecutor(new RushCmdHandler());
        rushConfig = getConfig();
        new SettingManager().SetUp();
        RushInstances.preLoad();
        ListenerRegister.register();
        ConfigLoader.loadSettings();
        AreaLoader.load();
        new MatchingSystem().runTaskTimer(this, 0, 20 * 3);
        new BroadCastHandler().runTaskTimer(this, 0, 13);
        Bukkit.getPluginManager().registerEvents(cps, MLGRush.instance);
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
        	new PaPiApi().hook();
        }
        else {
        	Bukkit.getPluginManager().disablePlugin(instance);
        }
        Bukkit.getPluginCommand("cps").setExecutor(new CpsHandler());
        MLGRush.serverName = this.getConfig().getString("ServerName", "MLGRush");
        MLGRush.ip = this.getConfig().getString("ip", "localhost:25565");
        RushInstances.APPLE_PREFIX = "§7[§d§l§m§n☆" + serverName + "☆§7] ";
        RushInstances.RUSH_PREFIX = "§7[§d§l§m§n☆" + serverName + "☆§7] ";
        RushInstances.BROAD_CAST = "§4§l→§b ☆" + serverName + "☆ §7| §e☆Rush New Century☆ §4§l←";
    }

    /**
     * 保存config
     */

    public static void saveSettings() {
        instance.saveConfig();
    }

    /**
     * 开始cps监控的ScoreBoard
     */

//    public static void startCPSMonitor() {
//        Bukkit.getScheduler().runTaskTimer(MLGRush.instance, () -> {
//            for (Player p : Bukkit.getOnlinePlayers()) {
//                Scoreboard board
//                        = Bukkit.getScoreboardManager().getNewScoreboard();
//                Objective objective = board.registerNewObjective("main", "dummy");
//                objective.setDisplayName("  §8§l« §3§l" + serverName +" §8§l»  ");
//                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
//                objective.getScore("").setScore(9);
//                objective.getScore("§7你在游玩: " + serverName).setScore(8);
//                objective.getScore("§m§r§f§k").setScore(7);
//                objective.getScore("§d").setScore(6);
//                objective.getScore("§7您在监测玩家的Cps是: ").setScore(5);
//                String instance = CpsHandler.map.getOrDefault(p.getName(), p.getName());
//                if (Bukkit.getPlayer(instance) == null) {
//                    objective.getScore("§c§l目标玩家不在线!").setScore(4);
//                } else {
//                    Player obj = Bukkit.getPlayer(instance);
//                    objective.getScore("§b"
//                            + MLGRush.instance.cps.getLeft(obj.getName())
//                            + " §7CPS(左键) / §b" +
//                            MLGRush.instance.cps.getRight(obj.getName())
//                            + " §7CPS(右键)").setScore(4);
//                }
//                objective.getScore("§7默认检测自己;").setScore(3);
//                objective.getScore("§7使用/cps <玩家名字>更换").setScore(2);
//                objective.getScore("§7→ 戒骄戒躁!").setScore(1);
//                objective.getScore("§e" + ip).setScore(0);
//                p.setScoreboard(board);
//            }
//            instance.cps.clearCps();
//        }, 0, 10);
//    }
}
