package net.kryunek.hub.utils.menu.buttons;

import lombok.AllArgsConstructor;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.FileConfig;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class BackButton extends Button {

    private final Menu back;
    private final FileConfig commonMenu = ModuleService.getFileModule().getFile("common_menu");

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.valueOf(commonMenu.getString("BACK.MATERIAL")))
                .name(commonMenu.getString("BACK.NAME"))
                .lore(commonMenu.getStringList("BACK.LORE"))
                .data(commonMenu.getInt("BACK.DATA"))
                .build();
    }

    @Override
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        Button.playNeutral(player);
        this.back.openMenu(player);
    }
}
