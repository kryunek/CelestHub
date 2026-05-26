package net.kryunek.hub.utils;

import org.bukkit.Location;
import org.bukkit.World;

public final class PvpArenaUtil {

    private PvpArenaUtil() {
    }

    public static boolean isArenaEnabled(FileConfig settingsConfig) {
        return settingsConfig != null && settingsConfig.getBoolean("PVP_ARENA.ENABLED");
    }

    public static boolean isInsideArena(FileConfig settingsConfig, Location location) {
        if (!isArenaEnabled(settingsConfig) || location == null || location.getWorld() == null) {
            return false;
        }

        String worldName = settingsConfig.getString("PVP_ARENA.WORLD", "", false);
        if (worldName == null || worldName.isBlank()) {
            return false;
        }

        World world = location.getWorld();
        if (!world.getName().equalsIgnoreCase(worldName)) {
            return false;
        }

        double x1 = settingsConfig.getDouble("PVP_ARENA.POS1.X");
        double y1 = settingsConfig.getDouble("PVP_ARENA.POS1.Y");
        double z1 = settingsConfig.getDouble("PVP_ARENA.POS1.Z");
        double x2 = settingsConfig.getDouble("PVP_ARENA.POS2.X");
        double y2 = settingsConfig.getDouble("PVP_ARENA.POS2.Y");
        double z2 = settingsConfig.getDouble("PVP_ARENA.POS2.Z");

        double minX = Math.min(x1, x2);
        double maxX = Math.max(x1, x2);
        double minY = Math.min(y1, y2);
        double maxY = Math.max(y1, y2);
        double minZ = Math.min(z1, z2);
        double maxZ = Math.max(z1, z2);

        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        return x >= minX && x <= maxX
                && y >= minY && y <= maxY
                && z >= minZ && z <= maxZ;
    }
}
