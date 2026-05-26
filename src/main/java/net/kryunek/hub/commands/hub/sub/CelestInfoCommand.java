package net.kryunek.hub.commands.hub.sub;

import net.kryunek.hub.Celest;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.command.CommandSender;

public class CelestInfoCommand extends BaseCommand {

    @Command(name = "celest.info", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        sender.sendMessage(CC.translate("&7&m------------------------------------------------"));
        sender.sendMessage(CC.translate("&b* &f&lCelest &b*"));
        sender.sendMessage(CC.translate(""));
        sender.sendMessage(CC.translate("&bAuthors: &f" + Celest.get().getDescription().getAuthors()));
        sender.sendMessage(CC.translate("&bVersion: &f" + Celest.get().getDescription().getVersion()));
        sender.sendMessage(CC.translate("&7&m------------------------------------------------"));
    }
}
