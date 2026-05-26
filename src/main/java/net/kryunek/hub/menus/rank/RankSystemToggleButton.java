package net.kryunek.hub.menus.rank;

import net.kryunek.hub.hook.TablistHook;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class RankSystemToggleButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        String configured = ModuleService.getManagerModule().getRankManager().getConfiguredSystemMode();
        String active = ModuleService.getManagerModule().getRankManager().getRankSystem();
        return new ItemBuilder(Material.COMPARATOR)
                .name(CC.translate("&bRank Provider"))
                .lore(Arrays.asList(
                        CC.translate("&7Configured: &f" + configured),
                        CC.translate("&7Active: &f" + active),
                        "",
                        CC.translate("&eLeft click: AUTO"),
                        CC.translate("&eRight click: DEFAULT"),
                        CC.translate("&eShift click: LUCKPERMS")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        String mode;
        if (clickType == ClickType.RIGHT) {
            mode = "DEFAULT";
        } else if (clickType == ClickType.SHIFT_LEFT || clickType == ClickType.SHIFT_RIGHT) {
            mode = "LUCKPERMS";
        } else {
            mode = "AUTO";
        }

        ModuleService.getManagerModule().getRankManager().setConfiguredSystemMode(mode);
        TablistHook.reload();
        playSuccess(player);
        new RankEditorMenu().openMenu(player);
    }
}
