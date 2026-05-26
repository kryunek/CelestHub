package net.kryunek.hub.managers.player;

import java.util.Map;
import java.util.UUID;

public interface ProfileStorage {
    ProfileData load(UUID uuid);
    Map<UUID, ProfileData> loadAll();
    void save(UUID uuid, ProfileData data);
    void close();
}
