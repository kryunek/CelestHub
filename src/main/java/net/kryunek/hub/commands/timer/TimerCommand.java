package net.kryunek.hub.commands.timer;

import net.kryunek.hub.commands.timer.sub.TimerCreateCommand;
import net.kryunek.hub.commands.timer.sub.TimerListCommand;
import net.kryunek.hub.commands.timer.sub.TimerManagerCommand;
import net.kryunek.hub.commands.timer.sub.TimerRemoveCommand;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class TimerCommand extends BaseCommand {

    @Command(name = "timer")
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        Player player = cmdArgs.getPlayer();
        String label = cmdArgs.getLabel();
        player.sendMessage(CC.translate("&7&m------------------------------------------------"));
        player.sendMessage(CC.translate("&b&lTimer Help"));
        player.sendMessage(CC.translate(""));
        player.sendMessage(CC.translate("&b/" + label + " create <queue> <seconds> <prefix> &7- &fCreate a timer."));
        player.sendMessage(CC.translate("&b/" + label + " remove <queue> &7- &fRemove timer."));
        player.sendMessage(CC.translate("&b/" + label + " list &7- &fSee all timers."));
        player.sendMessage(CC.translate("&b/" + label + " manager &7- &fManage with a menu."));
        player.sendMessage(CC.translate("&7&m------------------------------------------------"));
    }

    public TimerCommand() {
        new TimerListCommand();
        new TimerCreateCommand();
        new TimerRemoveCommand();
        new TimerManagerCommand();
    }
}
