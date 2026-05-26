package net.kryunek.hub.menus.lottery.list;

import lombok.AllArgsConstructor;
import net.kryunek.hub.managers.lottery.Lottery;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.menus.lottery.list.editor.LotteryEditorMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@AllArgsConstructor
public class LotteryPaginatedButton extends Button {

    private final String lotteryName;

    @Override
    public ItemStack getButtonItem(Player player) {
        Lottery lottery = ModuleService.getManagerModule().getLotteryManager().getLottery(lotteryName);
        boolean active = lottery != null && lottery.isActive();

        return new ItemBuilder(active ? Material.LIME_WOOL : Material.YELLOW_WOOL)
                .name(CC.translate("&dLottery: &f" + lotteryName))
                .lore(Arrays.asList(
                        CC.translate("&7Status: " + (active ? "&aActive" : "&eInactive")),
                        CC.translate("&7Time left: &f" + (active && lottery != null ? lottery.getRemainingSeconds() + "s" : "-")),
                        CC.translate("&7Participants: &f" + (lottery == null ? 0 : lottery.getParticipantCount())),
                        CC.translate("&7Winners: &f" + (lottery == null ? 1 : lottery.getWinnersCount())),
                        CC.translate("&7Rewards: &f" + (lottery == null ? 0 : lottery.getRewards().size())),
                        CC.translate("&7Duration: &f" + (lottery == null ? 0 : lottery.getDurationSeconds()) + "s"),
                        "",
                        CC.translate("&eClick to edit")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        playSuccess(player);
        new LotteryEditorMenu(lotteryName).openMenu(player);
    }
}
