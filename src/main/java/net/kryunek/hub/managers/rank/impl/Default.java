package net.kryunek.hub.managers.rank.impl;


import net.kryunek.hub.managers.rank.IRank;

import java.util.UUID;

public class Default implements IRank {

    @Override
    public String getName(UUID uuid) {
        return "Default";
    }

    @Override
    public String getPrefix(UUID uuid) {
        return "Default";
    }

    @Override
    public String getSuffix(UUID uuid) {
        return "Default";
    }

    @Override
    public String getColor(UUID uuid) {
        return "Default";
    }

    @Override
    public int getWeight(UUID uuid) {
        return 0;
    }
}