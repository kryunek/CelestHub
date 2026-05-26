package net.kryunek.hub.menus.particles;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.particles.TrailParticle;
import net.kryunek.hub.managers.particles.TrailParticleManager;
import net.kryunek.hub.managers.player.Profile;
import net.kryunek.hub.managers.player.ProfileManager;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class TrailParticleButton extends Button {
    private final TrailParticle trail;
    private final ProfileManager profileManager;
    private final TrailParticleManager trailManager;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemStack(this.trail.getIcon(player, profileManager.getProfile(player.getUniqueId())));
    }

    @Override
    public void clicked(Player player, int slot, ClickType type, int i) {
        Profile profile = this.profileManager.getProfile(player.getUniqueId());
        if (player.hasPermission(this.trail.getPermission())) {

            if (profile.getTrail() != null && profile.getTrail().equals(this.trail)) {
                Button.playFail(player);
                return;
            }
            profile.setTrail(this.trail);
            Button.playSuccess(player);
            player.closeInventory();
        }
        else if (player.hasPermission("celest.cosmetics.trail.*")) {
            if (profile.getTrail() != null && profile.getTrail().equals(this.trail)) {
                Button.playFail(player);
                return;
            }
            profile.setTrail(this.trail);
            Button.playSuccess(player);
            player.closeInventory();
        }
        else {
            Button.playFail(player);
        }
    }


    public TrailParticleButton(TrailParticle trail) {
        this.trail = trail;
        this.profileManager = ModuleService.getManagerModule().getProfileManager();
        this.trailManager = ModuleService.getManagerModule().getTrailParticleManager();

    }
}
