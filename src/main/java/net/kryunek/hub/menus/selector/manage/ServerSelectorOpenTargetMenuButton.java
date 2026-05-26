package net.kryunek.hub.menus.selector.manage;

import lombok.AllArgsConstructor;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@AllArgsConstructor
public class ServerSelectorOpenTargetMenuButton extends Button {

    private final String key;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.ENDER_EYE)
                .name(CC.translate("&bSet Target Queue"))
                .lore(Arrays.asList(
                        CC.translate("&7Select queue/server target"),
                        CC.translate("&7for this selector item."),
                        CC.translate("&7(ignored if decorative is enabled)"),
                        "",
                        CC.translate("&eClick to open queue list")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        playSuccess(player);
        new ServerSelectorServerTargetMenu(this.key, true).openMenu(player);
    }
}
