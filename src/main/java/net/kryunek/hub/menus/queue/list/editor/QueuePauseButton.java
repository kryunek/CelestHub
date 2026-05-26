package net.kryunek.hub.menus.queue.list.editor;

import lombok.AllArgsConstructor;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.queue.Queue;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@AllArgsConstructor
public class QueuePauseButton extends Button {

    private final String server;

    @Override
    public ItemStack getButtonItem(Player player) {

        Queue queue = ModuleService.getManagerModule().getQueueManager().getQueue(server);

        boolean paused = queue != null && queue.isPaused();

        return new ItemBuilder(paused ? Material.RED_WOOL : Material.LIME_WOOL)
                .name(CC.translate(paused ? "&cResume Queue" : "&aPause Queue"))
                .lore(Arrays.asList(
                        CC.translate("&7Click to toggle queue status")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        Queue queue = ModuleService.getManagerModule().getQueueManager().getQueue(server);

        if (queue == null) return;

        queue.setPaused(!queue.isPaused());
        playSuccess(player);
        player.sendMessage(CC.translate(
                queue.isPaused()
                        ? "&cQueue paused: &f" + server
                        : "&aQueue resumed: &f" + server
        ));
    }
}