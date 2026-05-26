package net.kryunek.hub.menus.outfit.manage.list.create;

import net.kryunek.hub.managers.outfit.OutfitCreateSession;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class OutfitPreviewButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        OutfitCreateSession.CreationData data = OutfitCreateEditorMenu.current(player);
        if (data == null) {
            return new ItemBuilder(Material.BARRIER).name(CC.translate("&cNo preview")).build();
        }

        return new ItemBuilder(Material.LEATHER_CHESTPLATE)
                .armorColor(Color.fromRGB(data.getRed(), data.getGreen(), data.getBlue()))
                .enchant(data.isEnchanted())
                .name(CC.translate("&ePreview"))
                .lore(Arrays.asList(
                        CC.translate("&7Name: &f" + data.getName()),
                        CC.translate("&7RGB: &f" + data.getRed() + ", " + data.getGreen() + ", " + data.getBlue()),
                        CC.translate("&7Enchanted: " + (data.isEnchanted() ? "&aYes" : "&cNo")),
                        "",
                        CC.translate("&7The armor on your player is updated live")
                ))
                .build();
    }
}
