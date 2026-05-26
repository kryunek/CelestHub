package net.kryunek.hub.menus.outfit.manage.list.create;

import net.kryunek.hub.menus.outfit.manage.list.OutfitPaginatedMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class OutfitCancelButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.BARRIER)
                .name(CC.translate("&cCancel Creation"))
                .lore(Arrays.asList(
                        CC.translate("&7Discard the current preview"),
                        "",
                        CC.translate("&bLeft click: &7Cancel")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        OutfitCreateEditorMenu.restorePreview(player);
        OutfitCreateEditorMenu.clear(player);
        playNeutral(player);
        player.sendMessage(CC.translate("&cOutfit creation cancelled."));
        new OutfitPaginatedMenu().openMenu(player);
    }
}
