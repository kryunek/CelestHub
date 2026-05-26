package net.kryunek.hub.menus.outfit.manage.list.editor;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.outfit.Outfit;
import net.kryunek.hub.managers.outfit.OutfitCreateSession;
import net.kryunek.hub.menus.outfit.manage.list.create.OutfitCreateEditorMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class OutfitEditColorButton extends Button {

    private final String outfitName;

    public OutfitEditColorButton(String outfitName) {
        this.outfitName = outfitName;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.LEATHER_CHESTPLATE)
                .name(CC.translate("&bEdit Outfit Color"))
                .lore(Arrays.asList(
                        CC.translate("&7Open color/enchant editor for &f" + this.outfitName),
                        "",
                        CC.translate("&eClick to edit")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        Outfit outfit = ModuleService.getManagerModule().getOutfitManager().getOutfit(this.outfitName);
        if (outfit == null) {
            playFail(player);
            player.sendMessage(CC.translate("&cOutfit not found."));
            return;
        }

        OutfitCreateSession.start(
                player,
                outfit.getName(),
                outfit.getName(),
                outfit.getRed(),
                outfit.getGreen(),
                outfit.getBlue(),
                outfit.isEnchanted()
        );

        playSuccess(player);
        new OutfitCreateEditorMenu().openMenu(player);
    }
}
