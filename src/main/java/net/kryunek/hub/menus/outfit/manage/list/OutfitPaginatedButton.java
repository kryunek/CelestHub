package net.kryunek.hub.menus.outfit.manage.list;

import lombok.AllArgsConstructor;
import net.kryunek.hub.managers.outfit.Outfit;
import net.kryunek.hub.menus.outfit.manage.list.editor.OutfitEditorMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@AllArgsConstructor
public class OutfitPaginatedButton extends Button {

    private final Outfit outfit;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.LEATHER_CHESTPLATE)
                .armorColor(this.outfit.getColor())
                .name(CC.translate("&b" + this.outfit.getName()))
                .lore(Arrays.asList(
                        CC.translate("&7RGB: &f" + this.outfit.getRed() + ", " + this.outfit.getGreen() + ", " + this.outfit.getBlue()),
                        CC.translate("&7Permission: &f" + this.outfit.getPermission()),
                        "",
                        CC.translate("&bLeft click: &7Open editor")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        new OutfitEditorMenu(this.outfit.getName()).openMenu(player);
        playSuccess(player);
    }
}
