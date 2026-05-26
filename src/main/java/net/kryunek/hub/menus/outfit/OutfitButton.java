package net.kryunek.hub.menus.outfit;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.outfit.Outfit;
import net.kryunek.hub.managers.player.Profile;
import net.kryunek.hub.managers.player.ProfileManager;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OutfitButton extends Button {

    private final Outfit outfit;
    private final ProfileManager profileManager;

    public OutfitButton(Outfit outfit) {
        this.outfit = outfit;
        this.profileManager = ModuleService.getManagerModule().getProfileManager();
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return this.outfit.getIcon(player, this.profileManager.getProfile(player.getUniqueId()));
    }

    @Override
    public void clicked(Player player, int slot, ClickType type, int i) {
        Profile profile = this.profileManager.getProfile(player.getUniqueId());
        if (!(player.hasPermission(this.outfit.getPermission()) || player.hasPermission("celest.cosmetics.outfit.*"))) {
            playFail(player);
            close(player);
            return;
        }

        if (profile.getOutfit() != null && profile.getOutfit().equals(this.outfit)) {
            playFail(player);
            return;
        }

        profile.setOutfit(this.outfit);
        ModuleService.getManagerModule().getOutfitManager().applySelectedOutfit(player, profile);
        playSuccess(player);
        player.closeInventory();
    }
}
