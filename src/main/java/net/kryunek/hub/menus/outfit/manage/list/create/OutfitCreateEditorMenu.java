package net.kryunek.hub.menus.outfit.manage.list.create;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.outfit.Outfit;
import net.kryunek.hub.managers.outfit.OutfitCreateSession;
import net.kryunek.hub.managers.player.Profile;
import net.kryunek.hub.menus.outfit.manage.list.OutfitPaginatedMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.Menu;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class OutfitCreateEditorMenu extends Menu {

    @Override
    public boolean isUpdateAfterClick() {
        return true;
    }

    @Override
    public String getTitle(Player player) {
        OutfitCreateSession.CreationData data = current(player);
        return CC.translate("&8Outfit Creator: " + (data == null ? "Preview" : data.getName()));
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        buttons.put(20, new OutfitColorMenuButton());
        buttons.put(22, new OutfitPreviewButton());
        buttons.put(24, new OutfitEnchantToggleButton());
        buttons.put(48, new OutfitSaveButton());
        buttons.put(49, new OutfitCancelButton());
        setPlaceholder(false);
        applyPreview(player);
        return buttons;
    }

    public static void applyPreview(Player player) {
        OutfitCreateSession.CreationData data = current(player);
        Profile profile = ModuleService.getManagerModule().getProfileManager().getProfile(player.getUniqueId());
        if (data == null || profile == null || profile.isBuildModeEnabled()) {
            return;
        }

        Color color = Color.fromRGB(data.getRed(), data.getGreen(), data.getBlue());
        player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).armorColor(color).enchant(data.isEnchanted()).build());
        player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).armorColor(color).enchant(data.isEnchanted()).build());
        player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).armorColor(color).enchant(data.isEnchanted()).build());
        player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).armorColor(color).enchant(data.isEnchanted()).build());
    }

    public static void restorePreview(Player player) {
        Profile profile = ModuleService.getManagerModule().getProfileManager().getProfile(player.getUniqueId());
        if (profile == null || profile.isBuildModeEnabled()) {
            return;
        }

        String previous = null;
        OutfitCreateSession.CreationData data = current(player);
        if (data != null) {
            previous = data.getPreviousOutfitName();
        }

        profile.setOutfit(previous == null ? null : Outfit.getOutfit(previous));
        ModuleService.getManagerModule().getOutfitManager().applySelectedOutfit(player, profile);
    }

    public static void store(Player player, OutfitCreateSession.CreationData data) {
        OutfitCreateSession.set(player, data);
    }

    static OutfitCreateSession.CreationData current(Player player) {
        return OutfitCreateSession.get(player);
    }

    static void update(Player player, OutfitCreateSession.CreationData data) {
        OutfitCreateSession.set(player, data);
    }

    static void clear(Player player) {
        OutfitCreateSession.stop(player);
    }

    @Override
    public void onClose(Player player) {
        super.onClose(player);
        if (isClosedByMenu()) {
            return;
        }
        if (!OutfitCreateSession.isActive(player)) {
            return;
        }
        restorePreview(player);
        clear(player);
    }
}
