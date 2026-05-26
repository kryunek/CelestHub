package net.kryunek.hub.menus.lottery.list.editor;

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
public class LotteryRewardEntryButton extends Button {

    private final String lotteryName;
    private final int rewardIndex;
    private final String reward;

    @Override
    public ItemStack getButtonItem(Player player) {
        String display = reward == null ? "" : reward;
        return new ItemBuilder(Material.PAPER)
                .name(CC.translate("&fReward #" + (rewardIndex + 1)))
                .lore(Arrays.asList(
                        CC.translate("&7" + display),
                        "",
                        CC.translate("&cClick to remove")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        boolean removed = ModuleService.getManagerModule().getLotteryManager().removeReward(lotteryName, rewardIndex);
        if (!removed) {
            playFail(player);
            player.sendMessage(CC.translate("&cCould not remove reward."));
            return;
        }

        playSuccess(player);
        player.sendMessage(CC.translate("&aReward removed from &f" + lotteryName + "&a."));
        new LotteryRewardsMenu(lotteryName).openMenu(player);
    }
}

