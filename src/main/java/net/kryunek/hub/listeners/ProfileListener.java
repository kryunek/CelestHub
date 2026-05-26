package net.kryunek.hub.listeners;

import net.kryunek.hub.Celest;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.player.Profile;
import net.kryunek.hub.managers.player.ProfileManager;
import net.kryunek.hub.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ProfileListener implements Listener {

    private final ProfileManager profileManager;

    public ProfileListener(Celest hub) {
        Bukkit.getPluginManager().registerEvents(this, hub);
        this.profileManager = ModuleService.getManagerModule().getProfileManager();
    }



    @EventHandler
    private void onPlayerSaveProfile(PlayerQuitEvent event) {
        Profile profile = this.profileManager.getProfile(event.getPlayer().getUniqueId());

        profile.save(true, false);

    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        Profile profile = this.profileManager.getProfile(event.getPlayer().getUniqueId());
        if (profile == null) {
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            event.setKickMessage(CC.translate("&cFailed in load your profile, please join again."));
            return;
        }
        profile.setName(event.getPlayer().getName());
        profile.save(false, true);
    }

    public ProfileListener() {
        this.profileManager = ModuleService.getManagerModule().getProfileManager();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        Profile profile = this.profileManager.createProfile(event.getUniqueId(), event.getName());
        profile.load();

    }
}
