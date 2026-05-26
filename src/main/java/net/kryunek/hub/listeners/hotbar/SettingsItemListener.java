package net.kryunek.hub.listeners.hotbar;

import net.kryunek.hub.Celest;
import net.kryunek.hub.managers.hotbar.Hotbar;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.menus.selector.ServerSelectorMenu;
import net.kryunek.hub.menus.settings.SettingsMenu;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SettingsItemListener implements Listener {



    public SettingsItemListener(Celest hub) {
        Bukkit.getPluginManager().registerEvents(this, hub);
    }

    @EventHandler
    private void onServer(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() == null) {
                return;
            }
            Hotbar hotbar = ModuleService.getManagerModule().getHotbarManager().getHotbar("SETTINGS");
            if (hotbar != null && hotbar.isEnabled() && hotbar.isHotbarItem(event.getItem())) {
                event.setCancelled(true);
                ModuleService.getManagerModule().getHotbarManager().playClickSound(event.getPlayer(), hotbar);
                new SettingsMenu().openMenu(event.getPlayer());
            }
        }
    }
}

