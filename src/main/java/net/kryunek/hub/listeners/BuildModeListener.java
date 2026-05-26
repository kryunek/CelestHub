package net.kryunek.hub.listeners;

import net.kryunek.hub.Celest;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.player.Profile;
import net.kryunek.hub.managers.player.ProfileManager;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class BuildModeListener implements Listener {

    private static final String ACTIONBAR_MESSAGE = "&bBuildMode &7- &fYou can build freely";

    private final ProfileManager profileManager;
    private final LegacyComponentSerializer serializer;

    public BuildModeListener(Celest hub) {
        Bukkit.getPluginManager().registerEvents(this, hub);
        this.profileManager = ModuleService.getManagerModule().getProfileManager();
        this.serializer = LegacyComponentSerializer.legacyAmpersand();

        Bukkit.getScheduler().runTaskTimer(hub, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Profile profile = this.profileManager.getProfile(player.getUniqueId());
                if (profile == null || !profile.isBuildModeEnabled()) {
                    continue;
                }

                player.sendActionBar(serializer.deserialize(ACTIONBAR_MESSAGE));
            }
        }, 20L, 20L);
    }
}
