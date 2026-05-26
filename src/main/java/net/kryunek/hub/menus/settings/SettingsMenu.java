package net.kryunek.hub.menus.settings;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.FileConfig;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SettingsMenu extends Menu {

    private final FileConfig settings_menuConfig = ModuleService.getFileModule().getFile("settings_menu");

    @Override
    public boolean isUpdateAfterClick() {
        return true;
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate(settings_menuConfig.getString("TITLE"));
    }

    @Override
    public int getSize() {
        return settings_menuConfig.getInt("SIZE");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> hashMap = new HashMap<>();
        if (settings_menuConfig.getBoolean("SETTINGS.TOGGLE_SCOREBOARD.ENABLED")) {
            hashMap.put(settings_menuConfig.getInt("SETTINGS.TOGGLE_SCOREBOARD.SLOT"), new SettingsButton(settings_menuConfig.getString("SETTINGS.TOGGLE_SCOREBOARD.NAME"), Material.valueOf(settings_menuConfig.getString("SETTINGS.TOGGLE_SCOREBOARD.ICON")), 0, settings_menuConfig.getStringList("SETTINGS.TOGGLE_SCOREBOARD.LORE"), "togglescoreboard", "scoreboardToggled"));
        }
        if (settings_menuConfig.getBoolean("SETTINGS.FLY_JOIN.ENABLED")) {
            hashMap.put(settings_menuConfig.getInt("SETTINGS.FLY_JOIN.SLOT"), new SettingsButton(settings_menuConfig.getString("SETTINGS.FLY_JOIN.NAME"), Material.valueOf(settings_menuConfig.getString("SETTINGS.FLY_JOIN.ICON")), 0, settings_menuConfig.getStringList("SETTINGS.FLY_JOIN.LORE"), "togglefly", "flyJoin"));
        }
        if (settings_menuConfig.getBoolean("SETTINGS.TOGGLE_TABLIST.ENABLED")) {
            hashMap.put(settings_menuConfig.getInt("SETTINGS.TOGGLE_TABLIST.SLOT"), new SettingsButton(settings_menuConfig.getString("SETTINGS.TOGGLE_TABLIST.NAME"), Material.valueOf(settings_menuConfig.getString("SETTINGS.TOGGLE_TABLIST.ICON")), 0, settings_menuConfig.getStringList("SETTINGS.TOGGLE_TABLIST.LORE"), "toggletablist", "tablistToggled"));
        }
        if (settings_menuConfig.getBoolean("SETTINGS.PARTICLES.ENABLED")) {
            hashMap.put(settings_menuConfig.getInt("SETTINGS.PARTICLES.SLOT"), new SettingsButton(settings_menuConfig.getString("SETTINGS.PARTICLES.NAME"), Material.valueOf(settings_menuConfig.getString("SETTINGS.PARTICLES.ICON")), 0, settings_menuConfig.getStringList("SETTINGS.PARTICLES.LORE"), "openParticles", "particles"));
        }
        if (settings_menuConfig.getBoolean("SETTINGS.OUTFIT.ENABLED")) {
            hashMap.put(settings_menuConfig.getInt("SETTINGS.OUTFIT.SLOT"), new SettingsButton(settings_menuConfig.getString("SETTINGS.OUTFIT.NAME"), Material.valueOf(settings_menuConfig.getString("SETTINGS.OUTFIT.ICON")), 0, settings_menuConfig.getStringList("SETTINGS.OUTFIT.LORE"), "openOutfit", "outfit"));
        }
        if (settings_menuConfig.getBoolean("SETTINGS.GADGETS.ENABLED")) {
            hashMap.put(settings_menuConfig.getInt("SETTINGS.GADGETS.SLOT"), new SettingsButton(settings_menuConfig.getString("SETTINGS.GADGETS.NAME"), Material.valueOf(settings_menuConfig.getString("SETTINGS.GADGETS.ICON")), 0, settings_menuConfig.getStringList("SETTINGS.GADGETS.LORE"), "openGadgets", "gadgets"));
        }
        if (settings_menuConfig.getBoolean("SETTINGS.TIME_PREFERENCE.ENABLED")) {
            hashMap.put(settings_menuConfig.getInt("SETTINGS.TIME_PREFERENCE.SLOT"), new SettingsButton(settings_menuConfig.getString("SETTINGS.TIME_PREFERENCE.NAME"), Material.valueOf(settings_menuConfig.getString("SETTINGS.TIME_PREFERENCE.ICON")), 0, settings_menuConfig.getStringList("SETTINGS.TIME_PREFERENCE.LORE"), "toggleTimePreference", "timePreference"));
        }
        if (settings_menuConfig.getBoolean("SETTINGS.JUKEBOX.ENABLED")) {
            hashMap.put(settings_menuConfig.getInt("SETTINGS.JUKEBOX.SLOT"), new SettingsButton(settings_menuConfig.getString("SETTINGS.JUKEBOX.NAME"), Material.valueOf(settings_menuConfig.getString("SETTINGS.JUKEBOX.ICON")), 0, settings_menuConfig.getStringList("SETTINGS.JUKEBOX.LORE"), "toggleJukebox", "jukebox"));
        }
        if (settings_menuConfig.getBoolean("SETTINGS.PVP_ARENA_KIT.ENABLED")) {
            hashMap.put(settings_menuConfig.getInt("SETTINGS.PVP_ARENA_KIT.SLOT"), new SettingsButton(settings_menuConfig.getString("SETTINGS.PVP_ARENA_KIT.NAME"), Material.valueOf(settings_menuConfig.getString("SETTINGS.PVP_ARENA_KIT.ICON")), 0, settings_menuConfig.getStringList("SETTINGS.PVP_ARENA_KIT.LORE"), "openPvpKitEditor", "pvpArenaKitEditor"));
        }
        if (player.hasPermission("celest.command.buildmode")) {
            if (settings_menuConfig.getBoolean("SETTINGS.BUILD_MODE.ENABLED")) {
                hashMap.put(settings_menuConfig.getInt("SETTINGS.BUILD_MODE.SLOT"), new SettingsButton(settings_menuConfig.getString("SETTINGS.BUILD_MODE.NAME"), Material.valueOf(settings_menuConfig.getString("SETTINGS.BUILD_MODE.ICON")), 0, settings_menuConfig.getStringList("SETTINGS.BUILD_MODE.LORE"), "togglebuildmode", "playerBuildMode"));
            }
        }

        setPlaceholder(settings_menuConfig.getBoolean("FILLER"));
        return hashMap;
    }
}

