package com.locydragon.mlgrush.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.lang.reflect.InvocationTargetException;

/**
 * @author TingChangHang
 * @date 2023/6/30 20:08
 * @desciption: 发送标题
 */
public class SendTitle implements Listener {
    static String version;

    public static Class getNmsClass(String name) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + version + "." + name);
    }

    public static Class getCbClass(String name) throws ClassNotFoundException {
        return Class.forName("org.bukkit.craftbukkit." + version + "." + name);
    }

    // 我们目前只需要获取这几个Class对象就好，放在外面他的作用域更大了，整个类都能调用了
    static Class iChatBaseComponent;
    static Class chatComponentText;
    static Class packet;
    static Class packetPlayOutTitle;
    static Class enumTitleAction;

    public static void getAllClass(){
        try {
            iChatBaseComponent = getNmsClass("IChatBaseComponent");
            chatComponentText = getNmsClass("ChatComponentText");
            packet = getNmsClass("Packet");
            packetPlayOutTitle = getNmsClass("PacketPlayOutTitle");
            // 因为EnumTitleAction这个枚举是PacketPlayOutTitle中的内部枚举，如果你看过内部类编译出来的class文件的名字的话，应该会知道
            enumTitleAction = getNmsClass("PacketPlayOutTitle$EnumTitleAction");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // var3为渐入时间/s  var4是停留时间/s  var5是渐出时间/s
    public static void sendTitle(Player p, String title, int var3, int var4, int var5){
        try {
            // 1. 获取ChatComponentText对象
            Object chatComponentTextInstance = chatComponentText.getConstructor(String.class).newInstance(title);
            // 2. 获取EnumTitleAction枚举TITLE
            Object enumTITLE = enumTitleAction.getMethod("a", String.class).invoke(enumTitleAction.getConstructor().newInstance(), "TITLE");
            // 3. 创建PacketPlayOutTitle对象
            Object packetPlayOutTitleInstance = packetPlayOutTitle.getConstructor(enumTitleAction, iChatBaseComponent, int.class, int.class, int.class).newInstance(enumTITLE, chatComponentTextInstance, var3, var4, var5);
            // 4. 使用CraftPlayer.class.cast(p) 把p对象强转一下
            Object craftPlayer = getCbClass("CraftPlayer").cast(p);
            // 5. 调用CraftPlayer中的getHandle方法获取EntityPlayer
            Object entityPlayer = craftPlayer.getClass().getMethod("getHandle").invoke(craftPlayer);
            // 6. 获取EntityPlayer中的成员变量PlayerConnection对象
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
            // 7. 调用sendPacket方法，传进去PacketPlayOutTitle对象
            playerConnection.getClass().getMethod("sendPacket",packet).invoke(playerConnection,packetPlayOutTitleInstance);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
