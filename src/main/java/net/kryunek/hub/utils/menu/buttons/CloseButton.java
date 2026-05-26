package net.kryunek.hub.utils.menu.buttons;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.FileConfig;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CloseButton extends Button {

    private final FileConfig commonMenu = ModuleService.getFileModule().getFile("common_menu");

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.valueOf(commonMenu.getString("CLOSE.MATERIAL")))
                .data(commonMenu.getInt("CLOSE.DATA"))
                .lore(commonMenu.getStringList("CLOSE.LORE"))
                .name(commonMenu.getString("CLOSE.NAME"))
                .build();
    }

    @Override
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        playNeutral(player);
        player.closeInventory();
    }
}
