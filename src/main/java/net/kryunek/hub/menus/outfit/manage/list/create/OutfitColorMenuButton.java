package net.kryunek.hub.menus.outfit.manage.list.create;

import net.kryunek.hub.managers.outfit.OutfitCreateSession;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class OutfitColorMenuButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        OutfitCreateSession.CreationData data = OutfitCreateEditorMenu.current(player);
        if (data == null) {
            return new ItemBuilder(Material.BARRIER)
                    .name(CC.translate("&cNo active creation"))
                    .build();
        }

        return new ItemBuilder(Material.LEATHER_CHESTPLATE)
                .armorColor(Color.fromRGB(data.getRed(), data.getGreen(), data.getBlue()))
                .name(CC.translate("&bColor Presets"))
                .lore(Arrays.asList(
                        CC.translate("&7Current RGB: &f" + data.getRed() + ", " + data.getGreen() + ", " + data.getBlue()),
                        "",
                        CC.translate("&bLeft click: &7Open the paginated colors menu")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (OutfitCreateEditorMenu.current(player) == null) {
            playFail(player);
            return;
        }

        playNeutral(player);
        new OutfitColorPaginatedMenu().openMenu(player);
    }
}
