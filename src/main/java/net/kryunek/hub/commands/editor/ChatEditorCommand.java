package net.kryunek.hub.commands.editor;

import net.kryunek.hub.menus.editor.chat.ChatEditorMenu;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;

public class ChatEditorCommand extends BaseCommand {

    @Command(name = "chateditor", permission = "celest.command.editor.chat")
    @Override
    public void onCommand(CommandArgs command) {
        new ChatEditorMenu().openMenu(command.getPlayer());
    }
}
