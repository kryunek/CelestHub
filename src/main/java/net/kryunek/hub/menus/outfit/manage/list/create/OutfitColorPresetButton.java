package net.kryunek.hub.menus.outfit.manage.list.create;

import lombok.AllArgsConstructor;
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

@AllArgsConstructor
public class OutfitColorPresetButton extends Button {

    private final String name;
    private final int red;
    private final int green;
    private final int blue;
    private final boolean returnToColorMenu;

    @Override
    public ItemStack getButtonItem(Player player) {
        OutfitCreateSession.CreationData data = OutfitCreateEditorMenu.current(player);
        boolean selected = data != null
                && data.getRed() == this.red
                && data.getGreen() == this.green
                && data.getBlue() == this.blue;

        return new ItemBuilder(Material.LEATHER_CHESTPLATE)
                .armorColor(Color.fromRGB(this.red, this.green, this.blue))
                .enchant(selected)
                .name(CC.translate((selected ? "&a" : "&b") + this.name))
                .lore(Arrays.asList(
                        CC.translate("&7RGB: &f" + this.red + ", " + this.green + ", " + this.blue),
                        CC.translate("&7Selected: " + (selected ? "&aYes" : "&cNo")),
                        "",
                        CC.translate("&bLeft click: &7Use this preset")
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

        OutfitCreateEditorMenu.update(player, data.withColor(this.red, this.green, this.blue));
        OutfitCreateEditorMenu.applyPreview(player);
        playSuccess(player);
        if (this.returnToColorMenu) {
            new OutfitColorPaginatedMenu().openMenu(player);
            return;
        }

        new OutfitCreateEditorMenu().openMenu(player);
    }
}
