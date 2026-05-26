package net.kryunek.hub.commands.outfit.sub;

import net.kryunek.hub.menus.outfit.manage.list.OutfitPaginatedMenu;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;

public class OutfitManagerCommand extends BaseCommand {

    @Command(name = "outfit.manager", aliases = {"outfits.manager"}, permission = "celest.command.outfit.manager", inGameOnly = true)
    @Override
    public void onCommand(CommandArgs command) {
        new OutfitPaginatedMenu().openMenu(command.getPlayer());
    }
}
