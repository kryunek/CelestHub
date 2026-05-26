package net.kryunek.hub.commands.timer.sub;

import net.kryunek.hub.menus.queue.QueueGlobalMenu;
import net.kryunek.hub.menus.timer.TimerPaginatedMenu;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;

public class TimerManagerCommand extends BaseCommand {

    @Command(name = "timer.manager", inGameOnly = true)
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        new TimerPaginatedMenu().openMenu(cmdArgs.getPlayer());
    }
}
