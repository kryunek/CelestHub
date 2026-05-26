package net.kryunek.hub.menus.particles.manage.list;

import lombok.AllArgsConstructor;
import net.kryunek.hub.managers.particles.TrailParticle;
import net.kryunek.hub.menus.particles.manage.list.editor.TrailParticleEditorMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@AllArgsConstructor
public class TrailParticlePaginatedButton extends Button {

    private final TrailParticle trailParticle;

    @Override
    public ItemStack getButtonItem(Player player) {
        Material material = Material.matchMaterial(this.trailParticle.getMaterial());
        if (material == null) {
            material = Material.BARRIER;
        }

        return new ItemBuilder(material)
                .data(this.trailParticle.getData())
                .name(CC.translate("&b" + this.trailParticle.getName()))
                .lore(Arrays.asList(
                        CC.translate("&7Particle: &f" + this.trailParticle.getEffect()),
                        CC.translate("&7Icon: &f" + this.trailParticle.getMaterial()),
                        CC.translate("&7Permission: &f" + this.trailParticle.getPermission()),
                        "",
                        CC.translate("&bLeft click: &7Open editor")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        new TrailParticleEditorMenu(this.trailParticle.getName()).openMenu(player);
        playSuccess(player);
    }
}
