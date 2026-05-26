package net.kryunek.hub.commands.queue;

import net.kryunek.hub.commands.queue.sub.QueueJoinCommand;
import net.kryunek.hub.commands.queue.sub.QueueLeaveCommand;
import net.kryunek.hub.commands.queue.sub.QueueListCommand;
import net.kryunek.hub.commands.queue.sub.QueueManagerCommand;
import net.kryunek.hub.commands.queue.sub.QueuePauseCommand;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class QueueCommand extends BaseCommand {

    @Command(name = "queue")
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        Player player = cmdArgs.getPlayer();
        String label = cmdArgs.getLabel();
        player.sendMessage(CC.translate("&7&m------------------------------------------------"));
        player.sendMessage(CC.translate("&b&lQueue Help"));
        player.sendMessage(CC.translate(""));
        player.sendMessage(CC.translate("&b/" + label + " join <queue> &7- &fJoin a queue."));
        player.sendMessage(CC.translate("&b/" + label + " leave <queue> &7- &fLeave a queue."));
        player.sendMessage(CC.translate("&b/" + label + " pause <queue> &7- &fPause a queue."));
        player.sendMessage(CC.translate("&b/" + label + " list &7- &fSee all queues."));
        player.sendMessage(CC.translate("&b/" + label + " manager &7- &fManage with a menu."));
        player.sendMessage(CC.translate("&7&m------------------------------------------------"));
    }

    public QueueCommand() {
        new QueueJoinCommand();
        new QueueLeaveCommand();
        new QueuePauseCommand();
        new QueueListCommand();
        new QueueManagerCommand();
    }
}
