package net.kryunek.hub.listeners;

import net.kryunek.hub.Celest;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.player.ProfileManager;
import net.kryunek.hub.menus.timer.TimerCreateSession;
import net.kryunek.hub.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TimerListener implements Listener {




    public TimerListener(Celest hub) {
        Bukkit.getPluginManager().registerEvents(this, hub);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (TimerCreateSession.isActive(player)) {
            event.setCancelled(true);
            TimerCreateSession.handleChat(player, event.getMessage());
        }
    }
    }
