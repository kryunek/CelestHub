package net.kryunek.hub.managers.module.impl;


import net.kryunek.hub.Celest;
import net.kryunek.hub.hook.ScoreboardHook;
import net.kryunek.hub.hook.TablistHook;
import net.kryunek.hub.managers.module.Module;
import net.kryunek.hub.managers.scoreboard.ScoreboardAnimation;

public class VisualsModule extends Module {

    @Override
    public void onEnable(Celest hub) {
            ScoreboardAnimation.init();
            ScoreboardHook.init(hub);
            TablistHook.init(hub);

    }

    @Override
    public int getPriority() {
        return 5;
    }
}
