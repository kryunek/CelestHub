package net.kryunek.hub.commands.queue.sub;

import net.kryunek.hub.menus.queue.QueueGlobalMenu;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;

public class QueueManagerCommand extends BaseCommand{

        @Command(name = "queue.manager", inGameOnly = true)
        @Override
        public void onCommand(CommandArgs cmdArgs) {
            new QueueGlobalMenu().openMenu(cmdArgs.getPlayer());
        }
    }
