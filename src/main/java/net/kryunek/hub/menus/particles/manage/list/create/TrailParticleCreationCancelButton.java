package net.kryunek.hub.menus.particles.manage.list.create;

import net.kryunek.hub.managers.particles.TrailParticleCreateSession;
import net.kryunek.hub.menus.particles.manage.list.TrailParticlePaginatedMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class TrailParticleCreationCancelButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.BARRIER)
                .name(CC.translate("&cCancel Creation"))
                .lore(Arrays.asList(
                        CC.translate("&7Cancel the current trail creation"),
                        "",
                        CC.translate("&cThis will discard the typed name")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        TrailParticleCreateSession.stop(player);
        playNeutral(player);
        player.sendMessage(CC.translate("&cTrail particle creation cancelled."));
        new TrailParticlePaginatedMenu().openMenu(player);
    }
}
