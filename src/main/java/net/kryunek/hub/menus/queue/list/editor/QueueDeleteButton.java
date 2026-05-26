package net.kryunek.hub.menus.queue.list.editor;

import lombok.AllArgsConstructor;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.queue.QueueManager;
import net.kryunek.hub.menus.queue.list.QueuePaginatedMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.ConfirmMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@AllArgsConstructor
public class QueueDeleteButton extends Button {

    private final String server;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.REDSTONE_TORCH)
                .name(CC.translate("&4Delete Queue"))
                .lore(Arrays.asList("&7Permanently delete this queue"))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        new ConfirmMenu(CC.translate("&8Confirm Delete"), confirmed -> {
            if (!confirmed) {
                new QueueEditorMenu(server).openMenu(player);
                return;
            }

            QueueManager manager = ModuleService.getManagerModule().getQueueManager();
            if (manager.getQueue(server) == null) {
                playFail(player);
                new QueuePaginatedMenu().openMenu(player);
                return;
            }

            manager.deleteQueue(server);
            playSuccess(player);
            player.sendMessage(CC.translate("&cQueue deleted: &f" + server));
            new QueuePaginatedMenu().openMenu(player);
        }, true).openMenu(player);
    }


}
