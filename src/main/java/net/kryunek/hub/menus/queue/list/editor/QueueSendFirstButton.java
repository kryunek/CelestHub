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

@AllArgsConstructor
public class QueueSendFirstButton extends Button {

    private final String server;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.IRON_DOOR)
                .name(CC.translate("&cSend First Player"))
                .lore(Arrays.asList("&7Send the first player of the queue"))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {

        Queue queue = ModuleService.getManagerModule().getQueueManager().getQueue(server);

        if (queue.getPlayerList().isEmpty()) {
            player.sendMessage(CC.translate("&cQueue is empty"));
            playFail(player);
            return;
        }

        queue.sendFirst();

        player.sendMessage(CC.translate("&cSent first person of the queue: &f" + server));
        playSuccess(player);
    }
}