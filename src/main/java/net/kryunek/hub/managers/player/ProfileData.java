package net.kryunek.hub.managers.player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileData {
    private String name;
    private boolean visibilityOn = true;
    private boolean showScoreboard = true;
    private boolean showTablist = true;
    private boolean buildModeEnabled = false;
    private boolean flyOnJoin = false;
    private boolean jukeboxEnabled = true;
    private double jukeboxVolume = 1.0D;
    private String trail = "None";
    private String outfit = "None";
    private String selectedGadgetType = "NONE";
    private String pvpArenaKitSerialized = "";
    private int pvpKills = 0;
    private int pvpDeaths = 0;
    private int pvpKillstreak = 0;
    private int pvpMaxKillstreak = 0;
    private long firstJoinAt = System.currentTimeMillis();
    private String timePreference = "SERVER";
}
