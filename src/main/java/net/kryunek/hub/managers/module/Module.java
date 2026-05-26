package net.kryunek.hub.managers.module;

import lombok.Getter;
import lombok.Setter;
import net.kryunek.hub.Celest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public abstract class Module {
    private int priority;
    @Getter
    private static final List<Module> modules;

    static {
        modules = new ArrayList<>();
    }

    public static List<Module> getOrderModules() {
        List<Module> modules = getModules();
        modules.sort(Comparator.comparingInt(Module::getPriority));
        return modules;
    }
    
    public Module() {
        Module.modules.add(this);
    }
    
    public abstract void onEnable(Celest hub);
    
}
