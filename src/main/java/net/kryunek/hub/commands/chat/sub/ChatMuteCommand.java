package net.kryunek.hub.commands.chat.sub;

import net.kryunek.hub.managers.chat.ChatManager;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.FileConfig;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.Bukkit;

public class ChatMuteCommand extends BaseCommand {

    private final FileConfig messages = ModuleService.getFileModule().getFile("messages");

    @Command(name = "chat.mute", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        if (!command.getSender().hasPermission("celest.command.chat.mute")
                && !command.getSender().hasPermission("celest.command.chat.pause")) {
            command.getSender().sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                    .getString("COMMAND.NO_PERMISSION")));
            return;
        }

        ChatManager chatManager = ModuleService.getManagerModule().getChatManager();
        chatManager.setPaused(true);
        String state = CC.translate("&cmuted");
        String message = CC.translate(messages.getString("CHAT.MESSAGES.TOGGLED_MUTE", "&eChat is now %state%&e.", true)
                .replace("%state%", state));
        Bukkit.broadcastMessage(message);
    }
}
