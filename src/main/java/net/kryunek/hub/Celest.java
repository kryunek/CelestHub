package net.kryunek.hub;

import lombok.Getter;
import lombok.Setter;
import net.kryunek.hub.managers.module.ModuleService;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public final class Celest extends JavaPlugin {

    private boolean isServerLoaded;

    @Override
    public void onEnable() {
        ModuleService.enable(this);
    }

    @Override
    public void onDisable() {
        ModuleService.disable(this);
    }

    public static Celest get() {
        return getPlugin(Celest.class);
    }

}
