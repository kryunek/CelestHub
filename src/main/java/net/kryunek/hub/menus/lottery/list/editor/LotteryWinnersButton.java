package net.kryunek.hub.menus.lottery.list.editor;

import lombok.AllArgsConstructor;
import net.kryunek.hub.managers.lottery.Lottery;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@AllArgsConstructor
public class LotteryWinnersButton extends Button {

    private final String lotteryName;

    @Override
    public ItemStack getButtonItem(Player player) {
        Lottery lottery = ModuleService.getManagerModule().getLotteryManager().getLottery(lotteryName);
        int winners = lottery == null ? 1 : Math.max(1, lottery.getWinnersCount());
        return new ItemBuilder(Material.PLAYER_HEAD)
                .name(CC.translate("&bWinners Count"))
                .lore(List.of(
                        CC.translate("&7Current: &f" + winners),
                        "",
                        CC.translate("&eLeft click: +1"),
                        CC.translate("&eRight click: -1"),
                        CC.translate("&eShift click: +5")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        Lottery lottery = ModuleService.getManagerModule().getLotteryManager().getLottery(lotteryName);
        if (lottery == null) {
            playFail(player);
            player.sendMessage(CC.translate("&cLottery not found."));
            return;
        }

        int current = Math.max(1, lottery.getWinnersCount());
        int next;
        if (clickType.isShiftClick()) {
            next = current + 5;
        } else if (clickType.isRightClick()) {
            next = Math.max(1, current - 1);
        } else {
            next = current + 1;
        }

        if (!ModuleService.getManagerModule().getLotteryManager().updateWinnersCount(lotteryName, next)) {
            playFail(player);
            player.sendMessage(CC.translate("&cCould not update winners count."));
            return;
        }

        playSuccess(player);
        player.sendMessage(CC.translate("&aWinners count set to &f" + next + "&a."));
    }
}
