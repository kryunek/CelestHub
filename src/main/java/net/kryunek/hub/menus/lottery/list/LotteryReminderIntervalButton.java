package net.kryunek.hub.menus.lottery.list;

import net.kryunek.hub.managers.lottery.LotteryReminderEditSession;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class LotteryReminderIntervalButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        int current = ModuleService.getManagerModule().getLotteryManager().getReminderIntervalSeconds();
        return new ItemBuilder(Material.CLOCK)
                .name(CC.translate("&bReminder Interval"))
                .lore(Arrays.asList(
                        CC.translate("&7Current: &f" + current + "s"),
                        "",
                        CC.translate("&eLeft click: +5s"),
                        CC.translate("&eRight click: -5s"),
                        CC.translate("&eShift click: set exact")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        int current = ModuleService.getManagerModule().getLotteryManager().getReminderIntervalSeconds();
        if (clickType == ClickType.SHIFT_LEFT || clickType == ClickType.SHIFT_RIGHT) {
            LotteryReminderEditSession.start(player);
            player.closeInventory();
            player.sendMessage(CC.translate("&eType reminder interval in seconds (>=5). Type 'cancel' to abort."));
            playNeutral(player);
            return;
        }

        int delta = clickType == ClickType.RIGHT ? -5 : 5;
        int updated = Math.max(5, current + delta);
        ModuleService.getManagerModule().getLotteryManager().updateReminderIntervalSeconds(updated);
        playSuccess(player);
        new LotteryPaginatedMenu().openMenu(player);
    }
}
