package net.kryunek.hub.menus.profile;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.player.Profile;
import net.kryunek.hub.managers.player.ProfileData;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.Menu;
import net.kryunek.hub.utils.menu.buttons.CloseButton;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ProfileMenu extends Menu {
    private final UUID targetUuid;
    private final ProfileData targetData;
    private final String forcedName;

    public ProfileMenu() {
        this(null, null, null);
    }

    public ProfileMenu(UUID targetUuid, ProfileData targetData, String forcedName) {
        this.targetUuid = targetUuid;
        this.targetData = targetData;
        this.forcedName = forcedName;
    }

    @Override
    public String getTitle(Player player) {
        String targetName = resolveDisplayName(player);
        boolean self = player.getName().equalsIgnoreCase(targetName);
        return CC.translate(self ? "&8Your Profile" : "&8Profile: &f" + targetName);
    }

    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        buttons.put(11, new ProfileInfoButton(targetUuid, targetData, forcedName));
        buttons.put(15, new OpenLeaderboardButton());
        buttons.put(22, new CloseButton());
        setPlaceholder(true);
        return buttons;
    }

    private String resolveDisplayName(Player viewer) {
        if (forcedName != null && !forcedName.isBlank()) {
            return forcedName;
        }
        if (targetData != null && targetData.getName() != null && !targetData.getName().isBlank()) {
            return targetData.getName();
        }
        return viewer.getName();
    }

    private static class ProfileInfoButton extends Button {
        private final UUID targetUuid;
        private final ProfileData targetData;
        private final String forcedName;

        private ProfileInfoButton(UUID targetUuid, ProfileData targetData, String forcedName) {
            this.targetUuid = targetUuid;
            this.targetData = targetData;
            this.forcedName = forcedName;
        }

        @Override
        public ItemStack getButtonItem(Player player) {
            ProfileData data = targetData;
            UUID headUuid = targetUuid;

            if (data == null) {
                Profile profile = ModuleService.getManagerModule().getProfileManager().getProfile(player.getUniqueId());
                if (profile != null) {
                    ProfileData snapshot = new ProfileData();
                    snapshot.setName(profile.getName());
                    snapshot.setPvpKills(profile.getPvpKills());
                    snapshot.setPvpDeaths(profile.getPvpDeaths());
                    snapshot.setPvpKillstreak(profile.getPvpKillstreak());
                    snapshot.setPvpMaxKillstreak(profile.getPvpMaxKillstreak());
                    data = snapshot;
                    headUuid = profile.getUuid();
                } else {
                    data = new ProfileData();
                    data.setName(player.getName());
                    headUuid = player.getUniqueId();
                }
            }

            int kills = data.getPvpKills();
            int deaths = data.getPvpDeaths();
            int streak = data.getPvpKillstreak();
            int bestStreak = data.getPvpMaxKillstreak();
            double kd = deaths <= 0 ? kills : (double) kills / deaths;

            ItemBuilder builder = new ItemBuilder(Material.PLAYER_HEAD);
            String displayName = forcedName;
            if (displayName == null || displayName.isBlank()) {
                displayName = data.getName() == null || data.getName().isBlank() ? player.getName() : data.getName();
            }
            if (headUuid != null) {
                builder.owner(headUuid, displayName);
            } else if (displayName != null && !displayName.isBlank()) {
                builder.owner(displayName);
            }

            return builder
                    .name("&b" + displayName)
                    .lore(List.of(
                            "&7PvP Stats",
                            "",
                            "&bKills&7: &f" + kills,
                            "&bDeaths&7: &f" + deaths,
                            "&bK/D&7: &f" + String.format(java.util.Locale.US, "%.2f", kd),
                            "&bKillstreak&7: &f" + streak,
                            "&bBest Streak&7: &f" + bestStreak
                    ))
                    .build();
        }
    }

    private static class OpenLeaderboardButton extends Button {
        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.PAINTING)
                    .name("&bLeaderboards")
                    .lore(List.of(
                            "&7View global PvP rankings",
                            "",
                            "&eClick to open"
                    ))
                    .build();
        }

        @Override
        public void clicked(Player player, int slot, org.bukkit.event.inventory.ClickType clickType, int hotbarButton) {
            playNeutral(player);
            new LeaderboardMenu().openMenu(player);
        }
    }

}
