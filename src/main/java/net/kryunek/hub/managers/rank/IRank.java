package net.kryunek.hub.managers.rank;

import java.util.UUID;

public interface IRank {

    String getName(UUID uuid);
    String getPrefix(UUID uuid);
    String getSuffix(UUID uuid);
    String getColor(UUID uuid);
    int getWeight(UUID uuid);
}