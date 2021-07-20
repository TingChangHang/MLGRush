package com.locydragon.mlgrush.commands;

import com.locydragon.mlgrush.MLGRush;
import com.locydragon.mlgrush.core.AreaLoader;
import com.locydragon.mlgrush.core.GameArea;
import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.listeners.BedSetter;
import com.locydragon.mlgrush.utils.SetBedEnum;
import com.locydragon.mlgrush.utils.SetBedState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author LocyDragon
 * @version V1.0
 * 游戏设置/配置-总指令类
 */

public class RushCmdHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "只有玩家才能使用这个指令……");
            return false;
        }
        if (args.length <= 0) {
        	sender.sendMessage(RushInstances.RUSH_PREFIX + "§b此插件由LocyDragon制作    魔改By MC_xiafeng_233 插件遵循GPLv3协议进行开源");
            sender.sendMessage(RushInstances.RUSH_PREFIX + "请输入正确的参数! (/rush help)");
            
            sender.sendMessage("§a夏枫的B站空间:https://space.bilibili.com/396930389");
            sender.sendMessage("§a点个关注吧(想要破1000QWQ)");
            
            return false;
        }
        Player player = (Player)sender;
        if (args[0].equalsIgnoreCase("lobby") && player.hasPermission("rush.admin")) {
            RushInstances.RUSH_LOBBY = player.getLocation();
            MLGRush.rushConfig.set("Lobby", player.getLocation());
            MLGRush.saveSettings();
            sender.sendMessage(RushInstances.RUSH_PREFIX + "已经设置大厅了!");
        } else if (args[0].equalsIgnoreCase("createhelp")) {
        	sender.sendMessage("§e区域设置教程§c(注意大小写)");
        	sender.sendMessage("§6①首先→你需要一个建造完成的地图(要有两个床,两个出生点)");
        	sender.sendMessage("§6②来到你建造的地图");
        	sender.sendMessage("§6③输入/rush create <地图名称> —— 创造一个房间 ((不能一个地图属于两个房间))");
        	sender.sendMessage("§6④找到观战者会被传送过来的位置");
        	sender.sendMessage("§6输入/rush set <地图名称> MONITOR ");
        	sender.sendMessage("§6设置观战者的传送位置");
        	sender.sendMessage("§6⑤像圈地一样，把整个地图圈起来");
        	sender.sendMessage("§6第一个坐标使用");
        	sender.sendMessage("§6/rush set <地图名称> POS_A");
        	sender.sendMessage("§6第二个坐标使用");
        	sender.sendMessage("§6/rush set <地图名称> POS_B");
        	sender.sendMessage("§6⑥选择A队的出生点位置，输入");
        	sender.sendMessage("§6/rush set <地图名称> SPAWN_A");
        	sender.sendMessage("§6/rush set <地图名称> SPAWN_A");
        	sender.sendMessage("§6选择B队的出生点位置，输入");
        	sender.sendMessage("§6/rush set <地图名称> SPAWN_B");
        	sender.sendMessage("§6⑦选择A队的床(一个床有两格方块，所以:)");
        	sender.sendMessage("§6输入/rush set <地图名称> BED_A_HEAD");
        	sender.sendMessage("§c在创造模式下敲碎床头");
        	sender.sendMessage("§6输入/rush set <地图名称> BED_A_BUTTOM");
        	sender.sendMessage("§c在创造模式下敲碎床尾");
        	sender.sendMessage("§6⑧选择B队的床(一个床有两格方块，所以:)");
        	sender.sendMessage("§6输入/rush set <地图名称> BED_B_HEAD");
        	sender.sendMessage("§c在创造模式下敲碎床头");
        	sender.sendMessage("§6输入/rush set <地图名称> BED_B_BUTTOM");
        	sender.sendMessage("§c在创造模式下敲碎床尾");
        	sender.sendMessage("§6⑨使用/rush info <地图名称> 查看地图设置信息");
        	sender.sendMessage("§6如果显示如下(全部为\"√\"):");
        	sender.sendMessage("§6则说明配置成功");
        	sender.sendMessage("§6⑩回到大厅，开始匹配吧!(地图已经自动进入匹配队列啦!)");
        } else if (args[0].equalsIgnoreCase("help")) {
        	if(player.hasPermission("rush.admin")) {
        		sendHelp(sender);
        	}else {
        		sender.sendMessage("§a/cps <玩家名称> —— 监控玩家Cps(这个指令所有玩家都有权限)");
        	}
        } else if (args[0].equalsIgnoreCase("info") && player.hasPermission("rush.admin")) {
            String areaName = args[1];
            GameArea area = AreaLoader.getArea(areaName);
            if (area == null) {
                sender.sendMessage(RushInstances.RUSH_PREFIX + "找不到该区域!");
            } else {
                if (area.MONITOR != null) {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "观察者: √");
                } else {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "观察者: ×");
                }
                if (area.POS_A != null) {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "坐标①: √");
                } else {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "坐标①: ×");
                }
                if (area.POS_B != null) {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "坐标②: √");
                } else {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "坐标②: ×");
                }
                if (area.SPAWN_A != null) {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "出生点①: √");
                } else {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "出生点①: ×");
                }
                if (area.SPAWN_B != null) {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "出生点②: √");
                } else {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "出生点②: ×");
                }
                if (area.bedBlock_A.isOK()) {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "床①: √");
                } else {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "床①: ×");
                }
                if (area.bedBlock_B.isOK()) {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "床②: √");
                } else {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "床②: ×");
                }
                sender.sendMessage(RushInstances.RUSH_PREFIX + "这是 " + area.areaName + " 的所有信息.");
            }
        } else if (args[0].equalsIgnoreCase("set") && player.hasPermission("rush.admin")) {
            String areaName = args[1];
            String job = args[2];
            if (!AreaLoader.containsArea(areaName)) {
                sender.sendMessage(RushInstances.RUSH_PREFIX + "不存在这个区域");
                return false;
            }
            GameArea area = AreaLoader.getArea(areaName);
            switch (job) {
                case "MONITOR":
                    area.MONITOR = player.getLocation();
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "观察者: √");
                    break;
                case "POS_A" :
                    area.POS_A = player.getLocation();
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "坐标①: √");
                    break;
                case "POS_B" :
                    area.POS_B = player.getLocation();
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "坐标②: √");
                    break;
                case "SPAWN_A" :
                    area.SPAWN_A = player.getLocation();
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "出生点①: √");
                    break;
                case "SPAWN_B" :
                    area.SPAWN_B = player.getLocation();
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "出生点②: √");
                    break;
                case "BED_A_HEAD" :
                    BedSetter.states.put(player.getName().toLowerCase()
                            , new SetBedState(SetBedEnum.A_HEAD, areaName));
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "请破坏一个 床头 来设置!");
                    break;
                case "BED_A_BUTTOM" :
                    BedSetter.states.put(player.getName().toLowerCase()
                            , new SetBedState(SetBedEnum.A_BUTTOM, areaName));
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "请破坏一个 床尾 来设置!");
                    break;
                case "BED_B_HEAD" :
                    BedSetter.states.put(player.getName().toLowerCase()
                            , new SetBedState(SetBedEnum.B_HEAD, areaName));
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "请破坏一个 床头 来设置!");
                    break;
                case "BED_B_BUTTOM" :
                    BedSetter.states.put(player.getName().toLowerCase()
                            , new SetBedState(SetBedEnum.B_BUTTOM, areaName));
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "请破坏一个 床尾 来设置!");
                    break;
                default :
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "参数错误!");
                    break;
            }
            area.saveArea();
        } else if (args[0].equalsIgnoreCase("create") && player.hasPermission("rush.admin")) {
            if (args[1] == null) {
                return false;
            }
            if (AreaLoader.createArea(args[1])) {
                sender.sendMessage(RushInstances.RUSH_PREFIX + "创建成功!");
            } else {
                sender.sendMessage(RushInstances.RUSH_PREFIX + "这个区域名已经被使用了!");
            }
        } else if (args[0].equalsIgnoreCase("shutdown") && player.hasPermission("rush.admin")) {
            for (Player ps : Bukkit.getOnlinePlayers()) {
                ps.kickPlayer("");
            }
            Bukkit.getScheduler().runTaskLater(MLGRush.instance, () -> {
                Bukkit.shutdown();
            }, 20);
        }
        return false;
    }

	private void sendHelp(CommandSender sender) {
		sender.sendMessage("§b此插件由LocyDragon制作    魔改By MC_xiafeng_233 插件遵循GPLv3协议进行开源");
		sender.sendMessage("§a/cps <玩家名称> —— 监控玩家Cps(这个指令所有玩家都有权限)");
		sender.sendMessage("§a/rush lobby —— 设置脚下方块为大厅传送点");
		sender.sendMessage("§a/rush shutdown —— 安全关服(防止卡方块,请用本指令关服!)");
		sender.sendMessage("§a/rush create <区域名称> —— 新建一个游戏区域");
		sender.sendMessage("§a/rush set <区域名称>  <MONITOR/POS_A/POS_B/SPAWN_A/SPAWN_B/BED_A_HEAD/BED_A_BUTTOM/BED_B_HEAD/BED_B_BUTTOM> —— 设置某个区域的<观察者坐标/区域对角线A/区域对角线B/出生点A/出生点B/队伍A的床头/队伍A的床尾/队伍B的床头/队伍B的床尾/>");
		sender.sendMessage("§a/rush info <区域名称> —— 查看某个区域还有哪些东西没有设置");
		sender.sendMessage("§a/rush createhelp —— 创建帮助");
        sender.sendMessage("§a夏枫的B站空间:https://space.bilibili.com/396930389");
	}
}
