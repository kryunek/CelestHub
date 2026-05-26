package net.kryunek.hub.menus.outfit.manage.list.create;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.outfit.OutfitCreateSession;
import net.kryunek.hub.menus.outfit.manage.list.OutfitPaginatedMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class OutfitSaveButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.EMERALD_BLOCK)
                .name(CC.translate("&aSave Outfit"))
                .lore(Arrays.asList(
                        CC.translate("&7Create the outfit with the current preview"),
                        "",
                        CC.translate("&bLeft click: &7Save")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        OutfitCreateSession.CreationData data = OutfitCreateEditorMenu.current(player);
        if (data == null) {
            playFail(player);
            return;
        }

        boolean updated = ModuleService.getManagerModule().getOutfitManager()
                .updateOutfit(data.getName(), data.getRed(), data.getGreen(), data.getBlue(), data.isEnchanted());

        if (!updated) {
            boolean created = ModuleService.getManagerModule().getOutfitManager()
                    .createOutfit(data.getName(), data.getRed(), data.getGreen(), data.getBlue(), data.isEnchanted());
            if (!created) {
                playFail(player);
                player.sendMessage(CC.translate("&cCould not create the outfit."));
                return;
            }
        }

        OutfitCreateEditorMenu.restorePreview(player);
        OutfitCreateEditorMenu.clear(player);
        playSuccess(player);
        player.sendMessage(CC.translate((updated ? "&aOutfit updated: &f" : "&aOutfit created: &f") + data.getName()));
        new OutfitPaginatedMenu().openMenu(player);
    }
}
