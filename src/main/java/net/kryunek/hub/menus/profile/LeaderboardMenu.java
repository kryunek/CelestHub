package net.kryunek.hub.menus.profile;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.player.Profile;
import net.kryunek.hub.managers.player.ProfileData;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.Menu;
import net.kryunek.hub.utils.menu.buttons.BackButton;
import net.kryunek.hub.utils.menu.buttons.CloseButton;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LeaderboardMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return CC.translate("&8Leaderboards");
    }

    @Override
    public int getSize() {
        return 36;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(12, new TopButton(LeaderboardType.KILLS));
        buttons.put(13, new TopButton(LeaderboardType.DEATHS));
        buttons.put(14, new TopButton(LeaderboardType.KILL_STREAK));

        buttons.put(34, new BackButton(new ProfileMenu()));
        buttons.put(31, new CloseButton());

        setPlaceholder(false);
        return buttons;
    }

    private static class TopButton extends Button {
        private final LeaderboardType type;

        private TopButton(LeaderboardType type) {
            this.type = type;
        }

        @Override
        public ItemStack getButtonItem(Player player) {
            List<Map.Entry<UUID, ProfileData>> top = getSortedProfiles(type);
            List<String> lore = new ArrayList<>();
            lore.add("&8Top players");
            lore.add("");

            int max = Math.min(10, top.size());
            for (int i = 0; i < max; i++) {
                Map.Entry<UUID, ProfileData> entry = top.get(i);
                ProfileData data = entry.getValue();
                String name = data.getName() == null || data.getName().isBlank()
                        ? entry.getKey().toString().substring(0, 8)
                        : data.getName();
                lore.add(getPrefix(i + 1) + " &f" + name + " &8- &b" + type.extractValue(data));
            }

            if (max == 0) {
                lore.add("&8#1 &f--- &8- &b0");
                lore.add("&8#2 &f--- &8- &b0");
                lore.add("&8#3 &f--- &8- &b0");
            }

            return new ItemBuilder(type.getIcon())
                    .name("&b" + type.getLabel() + " Top 10")
                    .lore(lore)
                    .build();
        }
    }

    private enum LeaderboardType {
        KILLS("Kills", Material.DIAMOND_SWORD),
        DEATHS("Deaths", Material.SKELETON_SKULL),
        KILL_STREAK("Best Streak", Material.BLAZE_POWDER);

        private final String label;
        private final Material icon;

        LeaderboardType(String label, Material icon) {
            this.label = label;
            this.icon = icon;
        }

        public String getLabel() {
            return label;
        }

        public Material getIcon() {
            return icon;
        }

        public int extractValue(ProfileData profileData) {
            return switch (this) {
                case KILLS -> profileData.getPvpKills();
                case DEATHS -> profileData.getPvpDeaths();
                case KILL_STREAK -> profileData.getPvpMaxKillstreak();
            };
        }
    }

    private static List<Map.Entry<UUID, ProfileData>> getSortedProfiles(LeaderboardType type) {
        Map<UUID, ProfileData> all = new HashMap<>(ModuleService.getManagerModule().getProfileManager().getStorage().loadAll());

        for (Profile online : ModuleService.getManagerModule().getProfileManager().getProfiles().values()) {
            ProfileData data = new ProfileData();
            data.setName(online.getName());
            data.setPvpKills(online.getPvpKills());
            data.setPvpDeaths(online.getPvpDeaths());
            data.setPvpKillstreak(online.getPvpKillstreak());
            data.setPvpMaxKillstreak(online.getPvpMaxKillstreak());
            all.put(online.getUuid(), data);
        }

        List<Map.Entry<UUID, ProfileData>> profiles = new ArrayList<>(all.entrySet());
        profiles.sort(buildComparator(type));
        return profiles;
    }

    private static Comparator<Map.Entry<UUID, ProfileData>> buildComparator(LeaderboardType type) {
        return switch (type) {
            case KILLS -> (a, b) -> {
                int cmp = Integer.compare(b.getValue().getPvpKills(), a.getValue().getPvpKills());
                if (cmp != 0) return cmp;
                cmp = Integer.compare(b.getValue().getPvpMaxKillstreak(), a.getValue().getPvpMaxKillstreak());
                if (cmp != 0) return cmp;
                return String.CASE_INSENSITIVE_ORDER.compare(
                        safeName(a.getValue()),
                        safeName(b.getValue())
                );
            };
            case DEATHS -> (a, b) -> {
                int cmp = Integer.compare(b.getValue().getPvpDeaths(), a.getValue().getPvpDeaths());
                if (cmp != 0) return cmp;
                cmp = Integer.compare(b.getValue().getPvpKills(), a.getValue().getPvpKills());
                if (cmp != 0) return cmp;
                return String.CASE_INSENSITIVE_ORDER.compare(
                        safeName(a.getValue()),
                        safeName(b.getValue())
                );
            };
            case KILL_STREAK -> (a, b) -> {
                int cmp = Integer.compare(b.getValue().getPvpMaxKillstreak(), a.getValue().getPvpMaxKillstreak());
                if (cmp != 0) return cmp;
                cmp = Integer.compare(b.getValue().getPvpKills(), a.getValue().getPvpKills());
                if (cmp != 0) return cmp;
                return String.CASE_INSENSITIVE_ORDER.compare(
                        safeName(a.getValue()),
                        safeName(b.getValue())
                );
            };
        };
    }

    private static String safeName(ProfileData data) {
        if (data == null || data.getName() == null) {
            return "";
        }
        return data.getName();
    }

    private static String getPrefix(int position) {
        return switch (position) {
            case 1 -> "&6#1";
            case 2 -> "&7#2";
            case 3 -> "&c#3";
            default -> "&8#" + position;
        };
    }
}
