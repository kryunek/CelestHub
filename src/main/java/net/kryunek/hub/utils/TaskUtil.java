package net.kryunek.hub.utils;

import net.kryunek.hub.Celest;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;


public class TaskUtil {

        private static final JavaPlugin plugin;

        public static void runLater(Runnable runnable, long timer) {
            Bukkit.getServer().getScheduler().runTaskLater(TaskUtil.plugin, runnable, timer);
        }

        static {
            plugin = Celest.get();
        }

        public static void runTaskTimer(Runnable runnable, long timer, long async) {
            Bukkit.getServer().getScheduler().runTaskTimer(TaskUtil.plugin, runnable, 20L * timer, 20L * async);
        }

        public static void runTimer(Runnable runnable, long timer, long async) {
            Bukkit.getServer().getScheduler().runTaskTimer(TaskUtil.plugin, runnable, timer, async);
        }

        public static BukkitTask runSyncTimer(Runnable runnable, long delay, long period) {
        return Bukkit.getServer().getScheduler().runTaskTimer(TaskUtil.plugin, runnable, delay, period);
    }

        public static void runTimerAsync(BukkitRunnable runnable, long timer, long async) {
            runnable.runTaskTimerAsynchronously(TaskUtil.plugin, timer, async);
        }

        public static void runLaterAsync(Runnable runnable, long async) {
            Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(TaskUtil.plugin, runnable, async);
        }

        public static void runTimer(BukkitRunnable runnable, long timer, long async) {
            runnable.runTaskTimer(TaskUtil.plugin, timer, async);
        }

        public static void runTimerAsync(Runnable runnable, long timer, long async) {
            Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(TaskUtil.plugin, runnable, timer, async);
        }

        public static void run(Runnable runnable) {
            Bukkit.getServer().getScheduler().runTask(TaskUtil.plugin, runnable);
        }

        public static void runAsync(Runnable runnable) {
            Bukkit.getServer().getScheduler().runTaskAsynchronously(TaskUtil.plugin, runnable);
        }

        public static void runTaskTimerAsynchronously(Runnable runnable, long timer, long async) {
            Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(TaskUtil.plugin, runnable, 20L * timer, 20L * async);
        }

        public static void scheduleSyncDelayedTask(Runnable runnable) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(TaskUtil.plugin, runnable);
        }
    }
