package net.kryunek.hub.commands.particles.sub;

import net.kryunek.hub.menus.particles.manage.list.TrailParticlePaginatedMenu;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;

public class TrailParticlesManagerCommand extends BaseCommand {

    @Command(
            name = "trail.manager",
            aliases = {"particles.manager", "particle.manager"},
            permission = "celest.command.trailparticles.manager",
            inGameOnly = true
    )
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        new TrailParticlePaginatedMenu().openMenu(cmdArgs.getPlayer());
    }
}
