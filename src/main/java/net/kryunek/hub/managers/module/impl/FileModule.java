package net.kryunek.hub.managers.module.impl;

import com.google.common.collect.Maps;
import lombok.Getter;
import net.kryunek.hub.Celest;
import net.kryunek.hub.managers.module.Module;
import net.kryunek.hub.utils.FileConfig;

import java.util.Map;

@Getter
public class FileModule extends Module {
    private final Map<String, FileConfig> files;

    public FileConfig getFile(String file) {
        return this.files.get(file);
    }
    
    @Override
    public int getPriority() {
        return 1;
    }
    
    public void reload() {
        for (FileConfig config : this.files.values()) {
            config.reload();
        }
    }
    
    @Override
    public void onEnable(Celest hub) {
        this.files.put("config", new FileConfig(hub, "core/config.yml"));
        this.files.put("players", new FileConfig(hub, "core/players.yml"));
        this.files.put("messages", new FileConfig(hub, "core/messages.yml"));
        this.files.put("hotbar", new FileConfig(hub, "features/hotbar.yml"));
        this.files.put("settings", new FileConfig(hub, "features/settings.yml"));
        this.files.put("scoreboard", new FileConfig(hub, "features/scoreboard.yml"));
        this.files.put("queue", new FileConfig(hub, "features/queue.yml"));
        this.files.put("lottery", new FileConfig(hub, "features/lottery.yml"));
        this.files.put("gadgets", new FileConfig(hub, "features/gadgets.yml"));
        this.files.put("particle", new FileConfig(hub, "features/particle.yml"));
        this.files.put("outfit", new FileConfig(hub, "features/outfit.yml"));
        this.files.put("tab", new FileConfig(hub, "features/tab.yml"));
        this.files.put("jukebox", new FileConfig(hub, "features/jukebox.yml"));
        this.files.put("common_menu", new FileConfig(hub, "menus/common.yml"));
        this.files.put("admin_menus", new FileConfig(hub, "menus/admin_menus.yml"));
        this.files.put("server_selector", new FileConfig(hub, "menus/server_selector.yml"));
        this.files.put("hub_selector", new FileConfig(hub, "menus/hub_selector.yml"));
        this.files.put("editor_menus", new FileConfig(hub, "menus/editor_menus.yml"));
        this.files.put("celest_editor", new FileConfig(hub, "menus/celest_editor.yml"));
        this.files.put("settings_menu", new FileConfig(hub, "menus/settings_menu.yml"));
    }
    
    public FileModule() {
        this.files = Maps.newHashMap();
    }
}
