package net.kryunek.hub.menus.rank;

import lombok.AllArgsConstructor;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@AllArgsConstructor
public class RankEditorButton extends Button {

    private final String rankName;

    @Override
    public ItemStack getButtonItem(Player player) {
        int queuePriority = ModuleService.getManagerModule().getRankManager().getQueuePriority(rankName);
        int tabPriority = ModuleService.getManagerModule().getRankManager().getTabPriority(rankName);

        return new ItemBuilder(Material.NAME_TAG)
                .name(CC.translate("&b" + rankName))
                .lore(Arrays.asList(
                        CC.translate("&7Queue priority: &f" + queuePriority),
                        CC.translate("&7Tab priority: &f" + tabPriority),
                        "",
                        CC.translate("&eClick to edit")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        playSuccess(player);
        new RankEntryEditorMenu(rankName).openMenu(player);
    }
}
