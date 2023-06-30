package com.locydragon.mlgrush.mcxiafeng;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import com.locydragon.mlgrush.MLGRush;
import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.core.team.TeamMatching;

import me.clip.placeholderapi.PlaceholderAPI;

public class SettingManager {
	
	public static File settingfile;
	public static YamlConfiguration setting;
	
	public static String lineformessage$StartTimer5 = "%RUSH_PERFIX%" +"比赛将在 5 秒后开始!";
	public static String lineformessage$StartTimer4 = "%RUSH_PERFIX%" +"比赛将在 4 秒后开始!";
	public static String lineformessage$StartTimer3 = "%RUSH_PERFIX%" +"比赛将在 3 秒后开始!";
	public static String lineformessage$StartTimer2 = "%RUSH_PERFIX%" +"比赛将在 2 秒后开始!";
	public static String lineformessage$StartTimer1 ="%RUSH_PERFIX%" + "比赛将在 1 秒后开始!";
	public static String lineformessage$Start = "战斗开始";
	public static String lineformessage$NoticeStart = "%RUSH_PERFIX%" + "%RUSH_PLAYERA%" + " 与 " + "%RUSH_PLAYERB%" + " 之间的战斗开始了!";
	public static String lineformessage$WaitingJoin = "%RUSH_APPLEPREFIX%" + "%player_name%" + " 加入匹配!";
	public static String lineformessage$ChatMessage = "§f[§d" + "%RUSH_SERVERNAME%" + "§f][§e☆§b" + "%player_name%"  + "§e☆§7>>> " + "";
	public static String lineformessage$ExitGame = "%RUSH_PERFIX%" + "%player_name%" + " 畏惧而逃!";
	public static String lineformessage$BeKickGame = "%RUSH_PERFIX%" + "%player_name%" + " 在战斗中服务器踢出!";
	public static String lineformessage$WinB = "§7§l§m§n==========================" + "\n" + "§4§l→ §eRush比赛: §b" + "%RUSH_PLAYERA%" + "§4§l→ §e战绩——§b" +  "%RUSH_PLAYERB%" + "§e胜出!§a(" + "%RUSH_SCOREB%" + "/" + "%RUSH_SCOREA%" + ") §4§l←" + " §c§lV.S. §b" + "%RUSH_PLAYERB%" + " §4§l←" +"\n" +"§7§l§m§n==========================";
	public static String lineformessage$WinA = "§7§l§m§n==========================" + "\n" + "§4§l→ §eRush比赛: §b" + "%RUSH_PLAYERB%" + "§4§l→ §e战绩——§b" +  "%RUSH_PLAYERA%" + "§e胜出!§a(" + "%RUSH_SCOREA%" + "/" + "%RUSH_SCOREB%" + ") §4§l←" + " §c§lV.S. §b" + "%RUSH_PLAYERA%" + " §4§l←" +"\n" +"§7§l§m§n==========================";
	public static String lineformessage$BreakSelfBed = "§c你没有办法拆掉自己的床";
	public static String lineformessage$CantfindRegion = "%RUSH_PERFIX%" + "区域不存在.";
	public static String lineformessage$SetingSuccess = "%RUSH_PERFIX%" + "设置成功!";
	public static String lineformessage$ArriveHeightest = "%RUSH_PERFIX%" + "已经到达最高建筑高度了!";
	public static String lineformessage$RoomFull = "%RUSH_PERFIX%" + "房间已满员……请等候!";
	public static String lineformessage$InQueue = "%RUSH_PERFIX%" + "已经加入匹配队列!请耐心等候，游戏不久后开始!";
	public static String lineformessage$InQueuePlayerSize = "%RUSH_PERFIX%" + "当前匹配人数: " + "%RUSH_MATCHINGPLAYERSIZE%" + "人(包括你自己).";
	public static String lineformessage$ExitQueue = "%RUSH_PERFIX%" + "已成功退出匹配!";
	public static String lineformessage$MathingEnded = "%RUSH_PERFIX%" + "该局比赛已经结束.";
	public static String lineformessage$ToBeSpecer = "%RUSH_PERFIX%" + "您已经进入观战模式. \n 使用 /spawn 来退出观战!";
	public static String lineformessage$BroadCast = "§4§l→ §c§l" + "%RUSH_PLAYERA%" + ": §b"
            + "%RUSH_SCOREA%" +
            "/10 §7分 | §a§l" + "%RUSH_PLAYERB%"
            + ": §b" + "%RUSH_SCOREB%" + "/10 §7分 §4§l←";
	
	public static String lineformessage$BedBreakMotd = "§e{PLAYERNAME}在本局获胜！";
	public static String lineformessage$LeaveGameMessage = "%RUSH_PERFIX%" + "你离开了游戏！";
	public static String lineforTeamName$TeamNameA = "§f[§c§l红队§f] §c" + "%RUSH_PLAYERA%";
	public static String lineforTeamName$TeamNameB = "§f[§a§l绿队§f] §a" + "%RUSH_PLAYERB%";
	
	
	public static String lineforItemName$MATCHING_ITEM = "§b§l→右键——即刻匹配←";
	public static String lineforItemName$CHANGEINV_ITEM = "§e§l更改背包";
	public static String lineforItemName$WATCHER_ITEM = "§a§l→即刻观战←";
	public static String lineforItemName$QUIT = "§c§l→取消匹配←";
	public static String lineforItemName$STICK = "§f击退棒";
	public static String lineforItemName$GLASS = "§f方块";
	public static String lineforItemName$AXE = "§f挖掘";
	public static String lineforItemName$SpecGuiArenaItem = "§c§l" + "%RUSH_PLAYERA%" + " §e§lV.S. §a§l" + "%RUSH_PLAYERB%";
	
	public static String lineforCore$RUSH_PREFIX = "§7[§d§l§m§n☆MLGRush☆§7] ";
	public static String lineforCore$APPLE_PREFIX = "§7[§d§l§m§n☆MLGRush☆§7] ";
	public static String lineforCore$BROAD_CAST = "§4§l→§b ☆MLGRush☆ §7| §e☆Rush New Century☆ §4§l←";
	public static String lineforCore$PlayerJoinNotice = "§7[§a§l+§7] " + "%player_name%";
	public static String lineforCore$PlayerLeaveNotice = "§7[§c§l-§7] " + "%player_name%";
	public static String lineforCore$PlayerJoinMsg = "%RUSH_APPLEPREFIX%" + "§7欢迎回家!" + "%player_name%" + ",戒骄戒躁!";
	
	public static String lineforGui$EditInv = "§e背包编辑";
	public static String lineforGui$Spec = "%RUSH_PERFIX%" + "观战面板";
	
	
	public static String lineforGuiBtn$Yes = "§a保存";
	public static String lineforGuiBtn$Cancel = "§c取消";
	public static String lineforGuiBtn$Reset = "§e重置";
	
	
	
	
	
	
	
	public void SetUp() {
		try {
		settingfile = new File(MLGRush.instance.getDataFolder(),"Setting.yml");
		if(!settingfile.exists()) {
			settingfile.createNewFile();
			setting = YamlConfiguration.loadConfiguration(settingfile);
			for (Field field : SettingManager.class.getDeclaredFields()) {
				String name = field.getName();
				if(name.startsWith("linefor")) {
					setting.set("message." + name.replace("linefor", "").replace("$", "."),field.get(this).toString());
				}
				
			}
			setting.save(settingfile);
		}else {
			setting = YamlConfiguration.loadConfiguration(settingfile);
		}
		
		for(Field field : SettingManager.class.getDeclaredFields()) {
			String name = field.getName();
			if(name.startsWith("linefor")) {
				field.set(String.class, setting.get("message." + name.replace("linefor", "").replace("$", ".")));
			}
			
		}
		
		} catch (IOException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
