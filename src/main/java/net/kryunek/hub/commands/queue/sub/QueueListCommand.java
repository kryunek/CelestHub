package net.kryunek.hub.commands.queue.sub;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.FileConfig;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Set;

public class QueueListCommand extends BaseCommand {

    private final FileConfig queueConfig;

    @Command(name = "queue.list")
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        Player player = cmdArgs.getPlayer();
        ConfigurationSection section = queueConfig.getConfiguration().getConfigurationSection("QUEUE.SERVERS");

        if (section == null) {
            player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages").getString("QUEUE.LIST_EMPTY")));
            return;
        }

        Set<String> queues = section.getKeys(false);
        player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages").getString("QUEUE.LIST_AVAILABLE").replace("%queues%", String.join(", ", queues))));
    }

    public QueueListCommand() {
        this.queueConfig = ModuleService.getFileModule().getFile("queue");
    }
}
