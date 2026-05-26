package net.kryunek.hub.managers.lottery;

import net.kryunek.hub.Celest;
import net.kryunek.hub.managers.session.SessionGuard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class LotteryReminderEditSession {

    private static final Set<UUID> ACTIVE = new HashSet<>();

    private LotteryReminderEditSession() {
    }

    public static void start(Player player) {
        if (!SessionGuard.canStart(player, "LOTTERY_REMINDER_EDIT")) {
            return;
        }
        UUID uuid = player.getUniqueId();
        ACTIVE.add(uuid);
        Bukkit.getScheduler().runTaskLater(Celest.get(), () -> ACTIVE.remove(uuid), 20L * 60L);
    }

    public static boolean isActive(Player player) {
        return ACTIVE.contains(player.getUniqueId());
    }

    public static void stop(Player player) {
        ACTIVE.remove(player.getUniqueId());
    }
}
