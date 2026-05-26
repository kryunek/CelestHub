package net.kryunek.hub.commands.hub.sub;

import net.kryunek.hub.menus.editor.CelestEditorMenu;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;

public class CelestEditorCommand extends BaseCommand {

    @Command(name = "celest.editor", permission = "celest.command.editor")
    @Override
    public void onCommand(CommandArgs command) {
        new CelestEditorMenu().openMenu(command.getPlayer());
    }
}
