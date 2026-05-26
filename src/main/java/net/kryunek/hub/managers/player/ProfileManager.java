package net.kryunek.hub.managers.player;

import com.google.common.collect.Maps;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.TaskUtil;

import java.util.Map;
import java.util.UUID;

public class ProfileManager {
    private final Map<UUID, Profile> profiles;
    private final ProfileStorage storage;

    public ProfileManager() {
        this.profiles = Maps.newHashMap();
        String type = ModuleService.getFileModule().getFile("config").getConfiguration().getString("PERSISTENCE.TYPE", "LOCAL");
        boolean enabled = ModuleService.getFileModule().getFile("config").getConfiguration().getBoolean("PERSISTENCE.ENABLED", false);
        if (enabled && "MONGO".equalsIgnoreCase(type)) {
            this.storage = new MongoProfileStorage();
        } else {
            this.storage = new LocalProfileStorage();
        }
        TaskUtil.runTimerAsync(() -> {
            for (Profile profile : this.profiles.values()) {
                if (profile == null) continue;
                profile.save(false, false);
            }
        }, 300L, 300L);
    }

    public Map<UUID, Profile> getProfiles() {
        return this.profiles;
    }


    public Profile createProfile(UUID uuid, String name) {
        Profile profile = new Profile(uuid, name);
        this.profiles.put(uuid, profile);
        return profile;
    }

    public void save() {
        for (Profile profile : this.profiles.values()) {
            profile.save(false, false);
        }
    }

    public ProfileStorage getStorage() {
        return storage;
    }

    public Profile getProfile(UUID uuid) {
        if (this.profiles.containsKey(uuid)) {
            return this.profiles.get(uuid);
        }
        return null;
    }

    public void shutdown() {
        storage.close();
    }
}

