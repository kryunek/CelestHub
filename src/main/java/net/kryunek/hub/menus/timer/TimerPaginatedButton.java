package net.kryunek.hub.menus.timer;

import lombok.AllArgsConstructor;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.timer.Timer;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@AllArgsConstructor
public class TimerPaginatedButton extends Button {

    private final Timer timer;

    @Override
    public ItemStack getButtonItem(Player player) {

        Material material;

        if (timer.isPaused()) {
            material = Material.YELLOW_WOOL;
        } else if (timer.isExpired()) {
            material = Material.RED_WOOL;
        } else {
            material = Material.LIME_WOOL;
        }

        return new ItemBuilder(material)
                .name(CC.translate(timer.getPrefix())) // 🔥 mejor usar prefix
                .lore(Arrays.asList(
                        CC.translate("&7Remaining: &f" + timer.getFormattedTime()),
                        CC.translate("&7Status: " + (timer.isPaused() ? "&ePaused" : "&aRunning")),
                        CC.translate("&7Queue: &a" + timer.getName()),
                        "",
                        CC.translate("&aLeft click: &7Pause/Resume"),
                        CC.translate("&cRight click: &7Delete")
                ))
                .build();
    }


    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {

        if (clickType.isLeftClick()) {
            ModuleService.getManagerModule().getTimerManager().togglePauseTimer(timer.getName());

            if (timer.isPaused()) {
                player.sendMessage(CC.translate("&cTimer paused."));
            } else {
                player.sendMessage(CC.translate("&aTimer resumed."));
            }

            playSuccess(player);
            return;
        }

        if (clickType.isRightClick()) {
            ModuleService.getManagerModule().getTimerManager().deleteTimer(player, timer.getName());
            playSuccess(player);
        }
    }
}
