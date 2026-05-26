package net.kryunek.hub.commands.chat.sub;

import net.kryunek.hub.managers.chat.ChatManager;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.FileConfig;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.Bukkit;

public class ChatSlowCommand extends BaseCommand {

    private final FileConfig messages = ModuleService.getFileModule().getFile("messages");

    @Command(name = "chat.slow", permission = "celest.command.chat.slow", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        String[] args = command.getArgs();
        ChatManager chatManager = ModuleService.getManagerModule().getChatManager();

        if (args.length < 1) {
            command.getSender().sendMessage(CC.translate("&cUsage: /chat slow <seconds|off>"));
            return;
        }

        if (args[0].equalsIgnoreCase("off")) {
            chatManager.setSlowSeconds(0);
            chatManager.clearChatCooldowns();
            Bukkit.broadcastMessage(CC.translate(messages.getString("CHAT.MESSAGES.DISABLED_SLOW", "&eChat slow mode disabled.", true)));
            return;
        }

        int seconds;
        try {
            seconds = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            command.getSender().sendMessage(CC.translate("&cUsage: /chat slow <seconds|off>"));
            return;
        }

        if (seconds <= 0) {
            chatManager.setSlowSeconds(0);
            chatManager.clearChatCooldowns();
            Bukkit.broadcastMessage(CC.translate(messages.getString("CHAT.MESSAGES.DISABLED_SLOW", "&eChat slow mode disabled.", true)));
            return;
        }

        chatManager.setSlowSeconds(seconds);
        chatManager.clearChatCooldowns();
        Bukkit.broadcastMessage(CC.translate(messages.getString("CHAT.MESSAGES.SET_SLOW", "&eChat slow mode set to &f%time%s&e.", true)
                .replace("%time%", String.valueOf(seconds))));
    }
}
