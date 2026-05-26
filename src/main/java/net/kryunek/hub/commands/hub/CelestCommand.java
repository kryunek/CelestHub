package net.kryunek.hub.commands.hub;

import net.kryunek.hub.commands.hub.sub.CelestInfoCommand;
import net.kryunek.hub.commands.hub.sub.CelestDebugCommand;
import net.kryunek.hub.commands.hub.sub.CelestEditorCommand;
import net.kryunek.hub.commands.hub.sub.CelestReloadCommand;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.command.CommandSender;

public class CelestCommand extends BaseCommand {

    public CelestCommand() {
        new CelestInfoCommand();
        new CelestDebugCommand();
        new CelestReloadCommand();
        new CelestEditorCommand();
    }

    @Command(name = "celest", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String label = command.getLabel();
        sender.sendMessage(CC.translate("&7&m------------------------------------------------"));
        sender.sendMessage(CC.translate("&f&lCelest"));
        sender.sendMessage(CC.translate(""));
        sender.sendMessage(CC.translate("&b/" + label + " info &fGives you info of the plugin."));
        sender.sendMessage(CC.translate("&b/" + label + " debug &fShows runtime debug information."));
        sender.sendMessage(CC.translate("&b/" + label + " reload &fReload the plugin."));
        sender.sendMessage(CC.translate("&b/" + label + " editor &fOpen global editor menu."));
        sender.sendMessage(CC.translate("&7&m------------------------------------------------"));
    }
}
