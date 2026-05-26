package net.kryunek.hub.managers.module.impl;


import net.kryunek.hub.Celest;
import net.kryunek.hub.commands.chat.ChatCommand;
import net.kryunek.hub.commands.hub.CelestCommand;
import net.kryunek.hub.commands.lottery.LotteryCommand;
import net.kryunek.hub.commands.others.BuildModeCommand;
import net.kryunek.hub.commands.others.FlyCommand;
import net.kryunek.hub.commands.others.LeaderboardCommand;
import net.kryunek.hub.commands.others.ProfileCommand;
import net.kryunek.hub.commands.others.JukeboxCommand;
import net.kryunek.hub.commands.outfit.OutfitCommand;
import net.kryunek.hub.commands.others.SettingsCommand;
import net.kryunek.hub.commands.particles.TrailParticlesCommand;
import net.kryunek.hub.commands.queue.QueueCommand;
import net.kryunek.hub.commands.spawn.SetSpawnCommand;
import net.kryunek.hub.commands.spawn.SpawnCommand;
import net.kryunek.hub.commands.timer.TimerCommand;
import net.kryunek.hub.managers.module.Module;
import net.kryunek.hub.utils.command.CommandManager;

import java.util.Collections;

public class CommandModule extends Module {
    @Override
    public int getPriority() {
        return 3;
    }
    
    @Override
    public void onEnable(Celest hub) {
        new CommandManager(hub, Collections.emptyList());
        new SetSpawnCommand();
        new SpawnCommand();
        new CelestCommand();
        new ChatCommand();
        new BuildModeCommand();
        new FlyCommand();
        new SettingsCommand();
        new ProfileCommand();
        new LeaderboardCommand();
        new JukeboxCommand();
        new OutfitCommand();
        new QueueCommand();
        new LotteryCommand();
        new TimerCommand();
        new TrailParticlesCommand();

    }
}
