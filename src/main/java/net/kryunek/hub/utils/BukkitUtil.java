package net.kryunek.hub.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class BukkitUtil {


    public static Location deserializeLocation(String location) {
        if (location == null) {
            return null;
        }
        String[] teleport = location.split(", ");
        if (teleport.length < 6) {
            return null;
        }
        World world = Bukkit.getWorld(teleport[0]);
        double x = Double.parseDouble(teleport[1]);
        double y = Double.parseDouble(teleport[2]);
        double z = Double.parseDouble(teleport[3]);
        float yaw = Float.parseFloat(teleport[4]);
        float pitch = Float.parseFloat(teleport[5]);
        return new Location(world, x, y, z, yaw, pitch);

    }
    
    public static String serializeItemStackArray(ItemStack[] stack) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);
            bukkitObjectOutputStream.writeInt(stack.length);
            long stacckk = stack.length;
            for (ItemStack stack1 : stack) {
                bukkitObjectOutputStream.writeObject(stack1);
            }
            bukkitObjectOutputStream.close();
            return Base64Coder.encodeLines(byteArrayOutputStream.toByteArray());
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    public static String getLocation(Location location) {
        if (location == null) {
            return null;
        }
        return location.getWorld().getName() + ", " + location.getX() + ", " + location.getY() + ", " + location.getZ();
    }
    
    public static ItemStack[] deserializeItemStackArray(String location) {
        if (location == null) {
            return new ItemStack[0];
        }
        if (location.equals("")) {
            return new ItemStack[0];
        }
        try {
            ByteArrayInputStream b = new ByteArrayInputStream(Base64Coder.decodeLines(location));
            BukkitObjectInputStream bukkit = new BukkitObjectInputStream(b);
            ItemStack[] stack = new ItemStack[bukkit.readInt()];
            for (int i = 0; i < stack.length; ++i) {
                stack[i] = (ItemStack)bukkit.readObject();
            }
            bukkit.close();
            return stack;
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ItemStack[0];
        }
    }

    
    public static String serializeLocation(Location location) {
        if (location == null) {
            return null;
        }
        return location.getWorld().getName() + ", " + location.getX() + ", " + location.getY() + ", " + location.getZ() + ", " + location.getYaw() + ", " + location.getPitch();
    }
}
