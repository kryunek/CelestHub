package net.kryunek.hub.listeners.hotbar;

import net.kryunek.hub.Celest;
import net.kryunek.hub.managers.hotbar.Hotbar;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.menus.hubselector.HubSelectorPaginatedMenu;
import net.kryunek.hub.menus.selector.ServerSelectorMenu;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ServerItemListener implements Listener {

    public ServerItemListener(Celest hub) {
        Bukkit.getPluginManager().registerEvents(this, hub);
    }

    @EventHandler
    private void onServer(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() == null) {
                return;
            }

            Hotbar hubSelector = ModuleService.getManagerModule().getHotbarManager().getHotbar("HUB_SELECTOR");
            if (hubSelector != null && hubSelector.isEnabled() && hubSelector.isHotbarItem(event.getItem())) {
                event.setCancelled(true);
                ModuleService.getManagerModule().getHotbarManager().playClickSound(event.getPlayer(), hubSelector);
                new HubSelectorPaginatedMenu().openMenu(event.getPlayer());
                return;
            }

            Hotbar serverSelector = ModuleService.getManagerModule().getHotbarManager().getHotbar("SERVER");
            if (serverSelector != null && serverSelector.isEnabled() && serverSelector.isHotbarItem(event.getItem())) {
                event.setCancelled(true);
                ModuleService.getManagerModule().getHotbarManager().playClickSound(event.getPlayer(), serverSelector);
                new ServerSelectorMenu().openMenu(event.getPlayer());
            }
        }
    }
}
