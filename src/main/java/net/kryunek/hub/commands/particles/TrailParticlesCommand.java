package net.kryunek.hub.commands.particles;

import net.kryunek.hub.commands.particles.sub.TrailParticlesManagerCommand;
import net.kryunek.hub.menus.particles.TrailParticlesMenu;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;

public class TrailParticlesCommand extends BaseCommand {

    @Command(name = "trail", aliases = {"particles", "particle"}, inGameOnly = true)
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        new TrailParticlesMenu().openMenu(cmdArgs.getPlayer());
    }

    public TrailParticlesCommand() {
        new TrailParticlesManagerCommand();
    }
}
