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

import java.util.Arrays;

@AllArgsConstructor
public class LotteryParticipantsButton extends Button {

    private final String lotteryName;

    @Override
    public ItemStack getButtonItem(Player player) {
        Lottery lottery = ModuleService.getManagerModule().getLotteryManager().getLottery(lotteryName);
        int count = lottery == null ? 0 : lottery.getParticipantCount();
        return new ItemBuilder(Material.PLAYER_HEAD)
                .name(CC.translate("&bParticipants"))
                .lore(Arrays.asList(
                        CC.translate("&7Joined players: &f" + count),
                        "",
                        CC.translate("&eClick to view list")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        playSuccess(player);
        new LotteryParticipantsMenu(lotteryName).openMenu(player);
    }
}
