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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@AllArgsConstructor
public class QueueClearButton extends Button {

    private final String server;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.MAP)
                .name(CC.translate("&cClear Queue"))
                .lore(Arrays.asList("&7Remove all players"))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {

        Queue queue = ModuleService.getManagerModule().getQueueManager().getQueue(server);

        if (queue == null) return;

        for (UUID target : new ArrayList<>(queue.getPlayerList())) {
            queue.removeEntry(target);
        }

        player.sendMessage(CC.translate("&cQueue cleared: &f" + server));
    }
}
