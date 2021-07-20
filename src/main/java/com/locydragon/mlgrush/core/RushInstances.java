package com.locydragon.mlgrush.core;

import com.locydragon.mlgrush.mcxiafeng.SettingManager;
import com.locydragon.mlgrush.utils.ItemBuilder;

import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

/**
 * @author LocyDragon
 * @version V1.0
 * 一些常量
 */

public class RushInstances {
    public static String RUSH_PREFIX = PlaceholderAPI.setPlaceholders(null, SettingManager.lineforCore$RUSH_PREFIX);
    public static String APPLE_PREFIX = PlaceholderAPI.setPlaceholders(null, SettingManager.lineforCore$APPLE_PREFIX);
    public static String BROAD_CAST = PlaceholderAPI.setPlaceholders(null, SettingManager.lineforCore$BROAD_CAST);
    public static Location RUSH_LOBBY = null;
    public static ItemStack MATCHING_ITEM = null;
    public static ItemStack CHANGEINV_ITEM = null;
    public static ItemStack WATCHER_ITEM = null;
    public static ItemStack QUIT = null;
    public static ItemStack STICK = null;
    public static ItemStack GLASS = null;
    public static ItemStack AXE = null;

    /**
     * 预加载常量(在onEnable被调用)
     */

    public static void preLoad() {
        MATCHING_ITEM = new ItemBuilder(Material.GOLD_AXE)
                .setDisplayName(PlaceholderAPI.setPlaceholders(null, SettingManager.lineforItemName$MATCHING_ITEM)).addEnchantment(Enchantment.KNOCKBACK, 5).build();
        CHANGEINV_ITEM = new ItemBuilder(Material.ENDER_CHEST)
        		.setDisplayName(PlaceholderAPI.setPlaceholders(null, SettingManager.lineforItemName$CHANGEINV_ITEM)).addEnchantment(Enchantment.KNOCKBACK, 5).build();
        WATCHER_ITEM = new ItemBuilder(Material.BED)
                .setDisplayName(PlaceholderAPI.setPlaceholders(null, SettingManager.lineforItemName$WATCHER_ITEM)).addEnchantment(Enchantment.ARROW_FIRE,5).build();
        QUIT = new ItemBuilder(Material.PAPER)
                .setDisplayName(PlaceholderAPI.setPlaceholders(null, SettingManager.lineforItemName$QUIT)).addEnchantment(Enchantment.ARROW_FIRE,5).build();
        STICK = new ItemBuilder(Material.STICK)
                .setDisplayName(PlaceholderAPI.setPlaceholders(null, SettingManager.lineforItemName$STICK)).addEnchantment(Enchantment.KNOCKBACK, 1).build();
        GLASS = new ItemBuilder(Material.HARD_CLAY)
                .setDisplayName(PlaceholderAPI.setPlaceholders(null, SettingManager.lineforItemName$GLASS)).addEnchantment(Enchantment.ARROW_FIRE, 1)
                .setAmount(64).build();
        AXE = new ItemBuilder(Material.DIAMOND_PICKAXE)
                .setDisplayName(PlaceholderAPI.setPlaceholders(null, SettingManager.lineforItemName$AXE)).addEnchantment(Enchantment.DIG_SPEED, 5)
                .setAmount(1).build();
    }
}
