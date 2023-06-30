package com.locydragon.mlgrush.commands;

import com.locydragon.mlgrush.core.data.InGame;
import com.locydragon.mlgrush.mcxiafeng.SettingManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class LeaveCmdHandler implements CommandExecutor {

    public static HashMap<String, String> map = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "只有玩家才能使用这个指令……");
            return false;
        } else {
            InGame.quit(((Player) sender).getPlayer());
            sender.sendMessage(SettingManager.lineformessage$LeaveGameMessage);
        }
    return false;
    }
}