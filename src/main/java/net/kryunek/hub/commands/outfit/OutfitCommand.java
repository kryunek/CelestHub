package net.kryunek.hub.commands.outfit;

import net.kryunek.hub.commands.outfit.sub.OutfitManagerCommand;
import net.kryunek.hub.menus.outfit.OutfitMenu;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;

public class OutfitCommand extends BaseCommand {

    @Command(name = "outfit", aliases = {"outfits"}, inGameOnly = true)
    @Override
    public void onCommand(CommandArgs command) {
        new OutfitMenu().openMenu(command.getPlayer());
    }

    public OutfitCommand() {
        new OutfitManagerCommand();
    }
}
