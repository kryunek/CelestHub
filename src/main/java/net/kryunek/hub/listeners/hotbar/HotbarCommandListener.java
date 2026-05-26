package net.kryunek.hub.listeners.hotbar;

import net.kryunek.hub.Celest;
import net.kryunek.hub.managers.hotbar.Hotbar;
import net.kryunek.hub.managers.module.ModuleService;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class HotbarCommandListener implements Listener {

    public HotbarCommandListener(Celest hub) {
        Bukkit.getPluginManager().registerEvents(this, hub);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInteract(PlayerInteractEvent event) {
        if (event.getItem() == null) {
            return;
        }
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        for (Hotbar hotbar : ModuleService.getManagerModule().getHotbarManager().getHotbars().values()) {
            if (!hotbar.isEnabled() || !hotbar.isHotbarItem(event.getItem())) {
                continue;
            }

            String command = hotbar.getCommand();
            if (command == null || command.isBlank()) {
                return;
            }

            String parsed = command.trim();
            if (parsed.startsWith("/")) {
                parsed = parsed.substring(1);
            }

            if (parsed.toLowerCase().startsWith("console:")) {
                String consoleCommand = parsed.substring("console:".length()).trim()
                        .replace("%player%", event.getPlayer().getName());
                if (!consoleCommand.isBlank()) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), consoleCommand);
                }
            } else {
                event.getPlayer().performCommand(parsed.replace("%player%", event.getPlayer().getName()));
            }

            ModuleService.getManagerModule().getHotbarManager().playClickSound(event.getPlayer(), hotbar);
            event.setCancelled(true);
            return;
        }
    }
}
