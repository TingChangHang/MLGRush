package com.locydragon.mlgrush.mcxiafeng;


import org.bukkit.entity.Player;

import com.locydragon.mlgrush.MLGRush;
import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.core.data.InGame;
import com.locydragon.mlgrush.core.team.TeamMatching;
import com.locydragon.mlgrush.listeners.CPSListener;

import me.clip.placeholderapi.external.EZPlaceholderHook;

@SuppressWarnings("deprecation")
public class PaPiApi extends EZPlaceholderHook{

	
	public PaPiApi() {
		super(MLGRush.instance, "RUSH");
	}
	
	@Override
	public String onPlaceholderRequest(Player p, String params) {
		if(p != null) {
			if(params.equals("CPS")) {
				return ""+new CPSListener().getLeft(p.getUniqueId().toString());
			}
			else if(params.equals("RPS")) {
				return ""+new CPSListener().getRight(p.getUniqueId().toString());
			}
			else if(params.equals("ISGAMEING")) {
				return InGame.isPlaying(p) ? "§a游戏中" : "§c闲置中";
			}
			else if(params.equals("ARENA")) {
				return InGame.get(p).area.areaName;
			}
			else if(params.equals("OPPONENT")) {
				Player OPPONENT;
				 OPPONENT = (InGame.get(p).area.A == p ? InGame.get(p).area.B : InGame.get(p).area.A);
				return OPPONENT.getName();
			}
			else if(params.equals("PERFIX")) {
				return RushInstances.RUSH_PREFIX;
			}
			else if(params.equals("APPLEPREFIX")) {
				return RushInstances.APPLE_PREFIX;
			}
			else if(params.equals("BROADCAST")) {
				return RushInstances.BROAD_CAST;
			}
			else if(params.equals("PLAYERA")) {
				return InGame.get(p.getUniqueId().toString()).area.A.getPlayer().getName();
			}
			else if(params.equals("PLAYERB")) {
				return InGame.get(p.getUniqueId().toString()).area.B.getPlayer().getName();
			}
			else if(params.equals("SCOREA")) {
				return ""+InGame.get(p.getUniqueId().toString()).area.a_Score;
			}
			else if(params.equals("SCOREB")) {
				return ""+InGame.get(p.getUniqueId().toString()).area.b_Score;
			}
			else if(params.equals("MATCHINGPLAYERSIZE")) {
				return ""+ TeamMatching.matchingPlayer.size();
			}
			else if(params.equals("SERVERNAME")) {
				return ""+ MLGRush.serverName;
			}
		}
		
		return "§c出错";
	}
	
	
	

}
