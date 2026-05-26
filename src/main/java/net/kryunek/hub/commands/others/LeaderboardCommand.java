package net.kryunek.hub.commands.others;

import net.kryunek.hub.menus.profile.LeaderboardMenu;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;

public class LeaderboardCommand extends BaseCommand {

    @Command(name = "leaderboard", aliases = {"top", "lb"}, inGameOnly = true)
    @Override
    public void onCommand(CommandArgs command) {
        new LeaderboardMenu().openMenu(command.getPlayer());
    }
}
