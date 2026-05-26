package net.kryunek.hub.menus.queue.list;

import lombok.AllArgsConstructor;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.queue.Queue;
import net.kryunek.hub.managers.queue.QueueCreateSession;
import net.kryunek.hub.managers.queue.QueueManager;
import net.kryunek.hub.menus.queue.list.editor.QueueEditorMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.FileConfig;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

@AllArgsConstructor
public class QueuePaginatedButton extends Button {

    private final String server;

    @Override
    public ItemStack getButtonItem(Player player) {

        Queue queue = ModuleService.getManagerModule().getQueueManager().getQueue(server);

        boolean paused = queue != null && queue.isPaused();
        int size = queue != null ? queue.getSize() : 0;

        // 🎨 Color según estado
        Material material;
        if (queue == null) {
            material = Material.RED_WOOL;
        } else if (paused) {
            material = Material.RED_WOOL;
        } else {
            material = Material.LIME_WOOL;
        }

        ItemBuilder item = new ItemBuilder(material)
                .name(CC.translate("&b" + server))
                .lore(Arrays.asList(
                        CC.translate("&7Players: &f" + size),
                        CC.translate("&7Status: " + (paused ? "&cPaused" : "&aJoinable")),
                        "",
                        CC.translate("&bLeft click: &7Open Editor")
                ));

        // ✨ Glow si está activa
        if (!paused && queue != null) {
        }

        return item.build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {

        Queue queue = ModuleService.getManagerModule().getQueueManager().getQueue(server);


        if (clickType.isLeftClick()) {

            if (queue == null) {
                playFail(player);
                player.sendMessage(CC.translate("&cQueue not found"));
                return;
            }
                new QueueEditorMenu(server).openMenu(player);
                playSuccess(player);
            }
        }

}