package com.locydragon.mlgrush.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.permissions.Permission;

import com.locydragon.mlgrush.MLGRush;
import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.mcxiafeng.SettingManager;
import com.locydragon.mlgrush.utils.ItemBuilder;

import me.clip.placeholderapi.PlaceholderAPI;

public class ChangeInvListener implements Listener {
	
	
	@EventHandler
	private void OnGuiItem(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if(event.getInventory().getName().equals(SettingManager.lineforGui$EditInv)) {
			if(event.getSlot() == 9) {
				try {
					 int STICKindex = 0;
					 int GLASSindex = 1;
					 int AXEindex = 2;
				for (int i = 0; i < 9; i++) {
					if(event.getInventory().getItem(i) != null) {
						
						if(event.getInventory().getItem(i).equals(RushInstances.STICK)) {
							STICKindex = i;
						}
						if(event.getInventory().getItem(i).equals(RushInstances.GLASS)) {
							GLASSindex = i;
						}
						if(event.getInventory().getItem(i).equals(RushInstances.AXE)) {
							AXEindex = i;
						}
					}
				}
				YamlConfiguration yaml = getPlayerInvConfig(player);
				yaml.set("STICKindex" , STICKindex);
				yaml.set("GLASSindex" , GLASSindex);
				yaml.set("AXEindex" , AXEindex);
				yaml.save(getPlayerInvFile(player));
					event.getWhoClicked().sendMessage(PlaceholderAPI.setPlaceholders(player, SettingManager.lineformessage$SetingSuccess));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(event.getSlot() == 10) {
				event.getWhoClicked().closeInventory();
			}else if(event.getSlot() == 11) {
				getPlayerInvFile(player).delete();
			}
			if(event.getSlot() >= 9) {
				event.setCancelled(true);
			}
		}
	}
	
	public static void OpenChangeInv(Player player) {
		Inventory inv = Bukkit.createInventory(null, 18,PlaceholderAPI.setPlaceholders(player, SettingManager.lineforGui$EditInv));
		inv.setItem(getPlayerInvConfig(player).getInt("STICKindex"), RushInstances.STICK);
		inv.setItem(getPlayerInvConfig(player).getInt("GLASSindex"), RushInstances.GLASS);
		inv.setItem(getPlayerInvConfig(player).getInt("AXEindex"), RushInstances.AXE);
		inv.setItem(9, new ItemBuilder(Material.WOOL).setdata((byte) 13).setDisplayName(PlaceholderAPI.setPlaceholders(player, SettingManager.lineforGuiBtn$Yes)).build());
		inv.setItem(10, new ItemBuilder(Material.WOOL).setdata((byte) 14).setDisplayName(PlaceholderAPI.setPlaceholders(player, SettingManager.lineforGuiBtn$Cancel)).build());
		inv.setItem(11, new ItemBuilder(Material.WOOL).setdata((byte) 4).setDisplayName(PlaceholderAPI.setPlaceholders(player, SettingManager.lineforGuiBtn$Reset)).build());
		for (int i = 12; i < 18; i++) {
			inv.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setdata((byte) 3).setDisplayName("§3-").build());
		}
        player.updateInventory();
		player.openInventory(inv);
	}
	
	private static File getPlayerInvFile(Player player) {
		File filed = new File(MLGRush.instance.getDataFolder(),"PlayerInventory");
		if(!filed.exists()) {
			filed.mkdirs();
		}
		File file = new File(filed,player.getUniqueId().toString()+".yml");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	public static YamlConfiguration getPlayerInvConfig(Player player) {
		YamlConfiguration yaml = null;
		try {
			yaml = YamlConfiguration.loadConfiguration(getPlayerInvFile(player));
			if(!(yaml.getKeys(false)).contains("STICKindex")) {
				yaml.set("STICKindex", 0);
				yaml.save(getPlayerInvFile(player));
			}
			if(!(yaml.getKeys(false)).contains("GLASSindex")) {
				yaml.set("GLASSindex", 1);
				yaml.save(getPlayerInvFile(player));
			}
			if(!(yaml.getKeys(false)).contains("AXEindex")) {
				yaml.set("AXEindex", 2);
				yaml.save(getPlayerInvFile(player));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return yaml;

	}

}
