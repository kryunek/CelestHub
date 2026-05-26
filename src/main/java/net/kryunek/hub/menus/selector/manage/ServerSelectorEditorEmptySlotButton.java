package net.kryunek.hub.menus.selector.manage;

import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ServerSelectorEditorEmptySlotButton extends Button {

    private final int slot;

    public ServerSelectorEditorEmptySlotButton(int slot) {
        this.slot = slot;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        if (ServerSelectorEditorMenu.isMoving(player)) {
            return new ItemBuilder(Material.LIME_STAINED_GLASS_PANE)
                    .name(CC.translate("&aMove here"))
                    .lore(Arrays.asList(
                            CC.translate("&7Target slot: &f" + slot),
                            "",
                            CC.translate("&eClick to place the selected item")
                    ))
                    .build();
        }

        return new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                .name(CC.translate("&7Empty Slot &8(" + slot + ")"))
                .lore(Arrays.asList(
                        CC.translate("&eClick to create selector item")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (!ServerSelectorEditorMenu.isMoving(player)) {
            String createdKey = ServerSelectorEditorMenu.createAtSlot(player, this.slot);
            playSuccess(player);
            player.sendMessage(CC.translate("&aCreated selector item: &f" + createdKey));
            new ServerSelectorItemEditorMenu(createdKey).openMenu(player);
            return;
        }

        ServerSelectorEditorMenu.moveToSlot(player, this.slot);
        playSuccess(player);
        new ServerSelectorEditorMenu().openMenu(player);
    }
}
