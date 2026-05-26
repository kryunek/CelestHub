package net.kryunek.hub.menus.particles.manage.list.editor;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.menus.particles.manage.list.TrailParticlePaginatedMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.ConfirmMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class TrailParticleDeleteButton extends Button {

    private final String trailName;

    public TrailParticleDeleteButton(String trailName) {
        this.trailName = trailName;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.BARRIER)
                .name(CC.translate("&cDelete Trail Particle"))
                .lore(Arrays.asList(
                        CC.translate("&7Delete trail particle &f" + this.trailName),
                        "",
                        CC.translate("&cThis action is immediate")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        new ConfirmMenu(CC.translate("&8Confirm Delete"), confirmed -> {
            if (!confirmed) {
                new TrailParticleEditorMenu(this.trailName).openMenu(player);
                return;
            }

            boolean deleted = ModuleService.getManagerModule().getTrailParticleManager().deleteTrail(this.trailName);
            if (!deleted) {
                playFail(player);
                player.sendMessage(CC.translate("&cTrail particle not found."));
                new TrailParticlePaginatedMenu().openMenu(player);
                return;
            }

            playSuccess(player);
            player.sendMessage(CC.translate("&aDeleted trail particle: &f" + this.trailName));
            new TrailParticlePaginatedMenu().openMenu(player);
        }, true).openMenu(player);
    }
}
