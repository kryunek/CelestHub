package net.kryunek.hub.menus.outfit.manage.list.create;

import net.kryunek.hub.managers.outfit.OutfitCreateSession;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class OutfitEnchantToggleButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        OutfitCreateSession.CreationData data = OutfitCreateEditorMenu.current(player);
        boolean enchanted = data != null && data.isEnchanted();
        return new ItemBuilder(enchanted ? Material.ENCHANTED_BOOK : Material.BOOK)
                .name(CC.translate(enchanted ? "&aEnchanted Enabled" : "&cEnchanted Disabled"))
                .lore(Arrays.asList(
                        CC.translate("&7Current: " + (enchanted ? "&aEnabled" : "&cDisabled")),
                        "",
                        CC.translate("&bLeft click: &7Toggle enchant glow")
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

        OutfitCreateEditorMenu.update(player, data.withEnchanted(!data.isEnchanted()));
        OutfitCreateEditorMenu.applyPreview(player);
        playSuccess(player);
        new OutfitCreateEditorMenu().openMenu(player);
    }
}
