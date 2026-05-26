package net.kryunek.hub.menus.lottery.list.editor;

import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class LotteryRewardsButton extends Button {

    private final String lotteryName;

    public LotteryRewardsButton(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.BOOK)
                .name(CC.translate("&bRewards List"))
                .lore(Arrays.asList(
                        CC.translate("&7View all rewards"),
                        CC.translate("&7Remove one by one"),
                        "",
                        CC.translate("&eClick to open")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        new LotteryRewardsMenu(lotteryName).openMenu(player);
        playSuccess(player);
    }
}

