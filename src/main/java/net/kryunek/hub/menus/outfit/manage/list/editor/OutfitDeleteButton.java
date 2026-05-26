package net.kryunek.hub.menus.outfit.manage.list.editor;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.menus.outfit.manage.list.OutfitPaginatedMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.ConfirmMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class OutfitDeleteButton extends Button {

    private final String outfitName;

    public OutfitDeleteButton(String outfitName) {
        this.outfitName = outfitName;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.BARRIER)
                .name(CC.translate("&cDelete Outfit"))
                .lore(Arrays.asList(
                        CC.translate("&7Delete outfit &f" + this.outfitName),
                        "",
                        CC.translate("&cThis action is immediate")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        new ConfirmMenu(CC.translate("&8Confirm Delete"), confirmed -> {
            if (!confirmed) {
                new OutfitEditorMenu(this.outfitName).openMenu(player);
                return;
            }

            boolean deleted = ModuleService.getManagerModule().getOutfitManager().deleteOutfit(this.outfitName);
            if (!deleted) {
                playFail(player);
                player.sendMessage(CC.translate("&cOutfit not found."));
                new OutfitPaginatedMenu().openMenu(player);
                return;
            }

            playSuccess(player);
            player.sendMessage(CC.translate("&aDeleted outfit: &f" + this.outfitName));
            new OutfitPaginatedMenu().openMenu(player);
        }, true).openMenu(player);
    }
}
