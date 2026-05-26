package net.kryunek.hub.commands.lottery.sub;

import net.kryunek.hub.menus.lottery.list.LotteryPaginatedMenu;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;

public class LotteryManagerCommand extends BaseCommand {

    @Command(name = "lottery.manager", aliases = {"loteria.manager", "cupon.manager", "coupon.manager"}, inGameOnly = true)
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        new LotteryPaginatedMenu().openMenu(cmdArgs.getPlayer());
    }
}
