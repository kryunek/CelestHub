package net.kryunek.hub.menus.rank;

import lombok.AllArgsConstructor;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.rank.RankEditSession;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@AllArgsConstructor
public class RankQueuePriorityButton extends Button {

    private final String rankName;

    @Override
    public ItemStack getButtonItem(Player player) {
        int current = ModuleService.getManagerModule().getRankManager().getQueuePriority(rankName);
        return new ItemBuilder(Material.CLOCK)
                .name(CC.translate("&bQueue Priority"))
                .lore(Arrays.asList(
                        CC.translate("&7Current: &f" + current),
                        "",
                        CC.translate("&eLeft click: +1"),
                        CC.translate("&eRight click: -1"),
                        CC.translate("&eShift click: set exact")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        int current = ModuleService.getManagerModule().getRankManager().getQueuePriority(rankName);
        if (clickType == ClickType.SHIFT_LEFT || clickType == ClickType.SHIFT_RIGHT) {
            RankEditSession.start(player, RankEditSession.Type.QUEUE_PRIORITY, rankName);
            player.closeInventory();
            player.sendMessage(CC.translate("&eType queue priority for &f" + rankName + "&e (>=0). Type 'cancel' to abort."));
            playNeutral(player);
            return;
        }

        int delta = clickType == ClickType.RIGHT ? -1 : 1;
        int updated = Math.max(0, current + delta);
        ModuleService.getManagerModule().getRankManager().setQueuePriority(rankName, updated);
        playSuccess(player);
        new RankEntryEditorMenu(rankName).openMenu(player);
    }
}
