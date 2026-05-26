package net.kryunek.hub.commands.editor;

import net.kryunek.hub.menus.editor.hotbar.HotbarEditorMenu;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;

public class HotbarEditorCommand extends BaseCommand {

    @Command(name = "hotbareditor", permission = "celest.command.editor.hotbar")
    @Override
    public void onCommand(CommandArgs command) {
        new HotbarEditorMenu().openMenu(command.getPlayer());
    }
}
