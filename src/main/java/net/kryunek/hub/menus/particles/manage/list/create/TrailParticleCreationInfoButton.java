package net.kryunek.hub.menus.particles.manage.list.create;

import net.kryunek.hub.managers.particles.TrailParticleCreateSession;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class TrailParticleCreationInfoButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        TrailParticleCreateSession.CreationData data = TrailParticleCreateSession.get(player);
        Material iconMaterial = data == null ? Material.BLAZE_POWDER : Material.matchMaterial(data.getMaterial());

        if (iconMaterial == null) {
            iconMaterial = Material.BLAZE_POWDER;
        }

        return new ItemBuilder(iconMaterial)
                .data(data == null ? 0 : data.getData())
                .name(CC.translate("&eCreation Preview"))
                .lore(Arrays.asList(
                        CC.translate("&7Name: &f" + (data == null ? "Unknown" : data.getTrailName())),
                        CC.translate("&7Icon: &f" + (data == null ? "BLAZE_POWDER" : data.getMaterial())),
                        "",
                        CC.translate("&7Choose a particle from this menu")
                ))
                .build();
    }
}
