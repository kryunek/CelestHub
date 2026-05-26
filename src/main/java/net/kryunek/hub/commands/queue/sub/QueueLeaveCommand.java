package net.kryunek.hub.commands.queue.sub;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.queue.Queue;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.FileConfig;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;

public class QueueLeaveCommand extends BaseCommand {


    private FileConfig queueConfig;
    private final LegacyComponentSerializer serializer = LegacyComponentSerializer.legacyAmpersand();

    @Command(name = "queue.leave")
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        Player player = cmdArgs.getPlayer();
        if (ModuleService.getManagerModule().getQueueManager().getQueue(player) == null) {
            player.sendMessage(CC.translate(queueConfig.getString("QUEUE_NOT_IN")));
            return;
        }
        Queue queue = ModuleService.getManagerModule().getQueueManager().getQueue(player);
        player.sendMessage(CC.translate(queueConfig.getString("QUEUE_LEAVE").replace("%queue%", queue.getServer())));
        sendLeaveActionBar(player, queue.getServer());
        ModuleService.getManagerModule().getQueueManager().getQueue(player).removeEntry(player);
    }

    public QueueLeaveCommand() {
        this.queueConfig = ModuleService.getFileModule().getFile("queue");
    }

    private void sendLeaveActionBar(Player player, String queueName) {
        FileConfig messages = ModuleService.getFileModule().getFile("messages");
        String prefix = messages.getString("ACTIONBAR.PREFIX", "", true);
        String message = messages.getString("ACTIONBAR.QUEUE_LEAVE", "", true);
        if (message == null || message.isEmpty()) {
            return;
        }
        player.sendActionBar(serializer.deserialize(CC.translate((prefix == null ? "" : prefix) + message.replace("%queue%", queueName))));
    }
}
