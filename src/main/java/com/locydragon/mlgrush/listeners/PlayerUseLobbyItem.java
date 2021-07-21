package com.locydragon.mlgrush.listeners;

import com.locydragon.mlgrush.MLGRush;
import com.locydragon.mlgrush.core.AreaLoader;
import com.locydragon.mlgrush.core.GameArea;
import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.core.data.GamePlayer;
import com.locydragon.mlgrush.core.data.InGame;
import com.locydragon.mlgrush.core.team.TeamMatching;
import com.locydragon.mlgrush.mcxiafeng.SettingManager;
import com.locydragon.mlgrush.utils.ItemBuilder;

import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import java.util.HashSet;
import java.util.Map;

/**
 * @author LocyDragon
 * @version V1.0
 * 匹配物品使用类
 */

public class PlayerUseLobbyItem implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getPlayer().getItemInHand() != null) {
        	if (e.getPlayer().getItemInHand().equals(RushInstances.CHANGEINV_ITEM)) {
        		ChangeInvListener.OpenChangeInv(e.getPlayer());
        	}
            if (e.getPlayer().getItemInHand().equals(RushInstances.WATCHER_ITEM)) {
                Inventory gui = Bukkit.createInventory(null, 27, PlaceholderAPI.setPlaceholders(e.getPlayer(), SettingManager.lineforGui$Spec));
                HashSet<GameArea> areaList = new HashSet<>();
                for (Map.Entry<String, GamePlayer> obj : InGame.gamePlayerHashMap.entrySet()) {
                    areaList.add(obj.getValue().area);
                }
                int i = 0;
                for (GameArea area : areaList) {
                    int a = area.a_Score;
                    int b = area.b_Score;
                    if (a < b) {
                        a = b;
                    }
                    gui.setItem(i, new ItemBuilder(Material.BED)
                            .setDisplayName(PlaceholderAPI.setPlaceholders(area.A, SettingManager.lineforItemName$SpecGuiArenaItem))
                            .addEnchantment(Enchantment.ARROW_FIRE, 1)
                            .setAmount(a).build());
                    i++;
                }
                e.getPlayer().openInventory(gui);
            } else if (e.getPlayer().getItemInHand().equals(RushInstances.MATCHING_ITEM)
                    && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                if (!AreaLoader.hasEmptyArea()) {
                    e.getPlayer().sendMessage(PlaceholderAPI.setPlaceholders(null, SettingManager.lineformessage$RoomFull));
                    return;
                }
                e.getPlayer().sendMessage(PlaceholderAPI.setPlaceholders(null, SettingManager.lineformessage$InQueue));
                TeamMatching.addWaiting(e.getPlayer());
                e.getPlayer().sendMessage(PlaceholderAPI.setPlaceholders(null, SettingManager.lineformessage$InQueuePlayerSize));
                e.getPlayer().getInventory().clear();
                e.getPlayer().getInventory().setItem(8, RushInstances.QUIT);
                e.getPlayer().updateInventory();
            } else if (e.getPlayer().getItemInHand().equals(RushInstances.QUIT)) {
                TeamMatching.removeWaiting(e.getPlayer());
                e.getPlayer().sendMessage(PlaceholderAPI.setPlaceholders(null, SettingManager.lineformessage$ExitQueue));
                e.getPlayer().getInventory().clear();
                e.getPlayer().getInventory().setItem(0, RushInstances.MATCHING_ITEM);
                e.getPlayer().getInventory().setItem(4, RushInstances.CHANGEINV_ITEM);
                e.getPlayer().getInventory().setItem(8, RushInstances.WATCHER_ITEM);
                e.getPlayer().updateInventory();
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        TeamMatching.removeWaiting(e.getPlayer());
    }

    @EventHandler
    public void onBeingKick(PlayerKickEvent e) {
        TeamMatching.removeWaiting(e.getPlayer());
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equals(PlaceholderAPI.setPlaceholders((Player) e.getWhoClicked(), SettingManager.lineforGui$Spec))) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.BED
            && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta()
            .getDisplayName().contains("V.S.")) {
                String name = ChatColor
                        .stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
                String[] amounts = name.replace("V.S.", "@").split("@");
                Player playerA = Bukkit.getPlayer(amounts[0].trim());
                Player playerB = Bukkit.getPlayer(amounts[1].trim());
                if (!playerA.isOnline() || !playerB.isOnline()) {
                    e.getWhoClicked().sendMessage(PlaceholderAPI.setPlaceholders( null,SettingManager.lineformessage$MathingEnded));
                    return;
                }
                if (!InGame.isPlaying(playerA) || !InGame.isPlaying(playerB)) {
                    e.getWhoClicked().sendMessage(PlaceholderAPI.setPlaceholders( null,SettingManager.lineformessage$MathingEnded));
                    return;
                }
                GameArea areaA = InGame.get(playerA).area;
                GameArea areaB = InGame.get(playerB).area;
                if (!areaA.areaName.equals(areaB.areaName)) {
                    e.getWhoClicked().sendMessage(PlaceholderAPI.setPlaceholders( null,SettingManager.lineformessage$MathingEnded));
                    return;
                }
                e.getWhoClicked().teleport(areaA.MONITOR);
                Bukkit.getScheduler().runTaskLater(MLGRush.instance, () -> {
                    e.getWhoClicked().setGameMode(GameMode.SPECTATOR);
                }, 10);
                e.getWhoClicked().sendMessage(PlaceholderAPI.setPlaceholders((Player) e.getWhoClicked(), SettingManager.lineformessage$ToBeSpecer));
            }
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        if (!e.getFrom().getWorld().getName().equals(e.getTo().getWorld().getName())
        && e.getTo().getWorld().getName().equals(RushInstances.RUSH_LOBBY.getWorld().getName())) {
            e.getPlayer().setGameMode(GameMode.SURVIVAL);
            InGame.quit(e.getPlayer());
        }
    }
}
