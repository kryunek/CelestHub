package net.kryunek.hub.managers.module;

import net.kryunek.hub.Celest;
import net.kryunek.hub.managers.module.impl.CommandModule;
import net.kryunek.hub.managers.module.impl.FileModule;
import net.kryunek.hub.managers.module.impl.ListenerModule;
import net.kryunek.hub.managers.module.impl.ManagerModule;
import net.kryunek.hub.managers.module.impl.VisualsModule;
import net.kryunek.hub.utils.TaskUtil;
import org.bukkit.Bukkit;

import java.util.List;

public class ModuleService {
    private static final ManagerModule managerModule;
    private static final FileModule fileModule;
    private static final CommandModule commandModule;
    private static final ListenerModule listenerModule;
    private static final VisualsModule visualModule;

    public static void disable(Celest hub) {
        if (managerModule.getNetworkSyncManager() != null) {
            managerModule.getNetworkSyncManager().shutdown();
        }
        managerModule.getProfileManager().save();
        managerModule.getProfileManager().shutdown();
    }

    public static void enable(Celest hub) {
        for (Module module : Module.getOrderModules()) {
            module.onEnable(hub);
        }


        TaskUtil.runLater(() -> hub.setServerLoaded(true), 100L);
    }

    public static FileModule getFileModule() {
        return ModuleService.fileModule;
    }

    public static ListenerModule getListenerModule() {
        return ModuleService.listenerModule;
    }

    public static VisualsModule getVisualModule() {
        return ModuleService.visualModule;
    }

    public static ManagerModule getManagerModule() {
        return ModuleService.managerModule;
    }

    public static CommandModule getCommandModule() {
        return ModuleService.commandModule;
    }

    static {
        fileModule = new FileModule();
        managerModule = new ManagerModule();
        listenerModule = new ListenerModule();
        commandModule = new CommandModule();
        visualModule = new VisualsModule();
    }
}
