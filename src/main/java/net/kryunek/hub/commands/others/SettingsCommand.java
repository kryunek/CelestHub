package net.kryunek.hub.commands.others;

import net.kryunek.hub.menus.settings.SettingsMenu;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;

public class SettingsCommand extends BaseCommand {

    @Command(name = "settings", inGameOnly = true)
    @Override
    public void onCommand(CommandArgs command) {
        new SettingsMenu().openMenu(command.getPlayer());
    }
}
