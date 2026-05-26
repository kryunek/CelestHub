package net.kryunek.hub.menus.particles.manage.list.editor;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.particles.TrailParticle;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class TrailParticleEditEffectButton extends Button {

    private final String trailName;

    public TrailParticleEditEffectButton(String trailName) {
        this.trailName = trailName;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.NETHER_STAR)
                .name(CC.translate("&bEdit Particle Effect"))
                .lore(Arrays.asList(
                        CC.translate("&7Change effect of &f" + this.trailName),
                        "",
                        CC.translate("&eClick to edit")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        TrailParticle trail = ModuleService.getManagerModule().getTrailParticleManager().getTrail(this.trailName);
        if (trail == null) {
            playFail(player);
            player.sendMessage(CC.translate("&cTrail particle not found."));
            return;
        }

        playSuccess(player);
        new TrailParticleEditEffectMenu(this.trailName).openMenu(player);
    }
}
