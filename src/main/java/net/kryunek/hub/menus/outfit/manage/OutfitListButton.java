package net.kryunek.hub.menus.outfit.manage;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.menus.outfit.manage.list.OutfitPaginatedMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.FileConfig;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OutfitListButton extends Button {

    private final FileConfig adminMenus = ModuleService.getFileModule().getFile("admin_menus");

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.valueOf(adminMenus.getString("OUTFIT.LIST_BUTTON.MATERIAL")))
                .name(CC.translate(adminMenus.getString("OUTFIT.LIST_BUTTON.NAME")))
                .lore(adminMenus.getStringList("OUTFIT.LIST_BUTTON.LORE"))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        new OutfitPaginatedMenu().openMenu(player);
        playSuccess(player);
    }
}
