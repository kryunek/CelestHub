package net.kryunek.hub.commands.chat;

import net.kryunek.hub.commands.chat.sub.ChatClearCommand;
import net.kryunek.hub.commands.chat.sub.ChatMuteCommand;
import net.kryunek.hub.commands.chat.sub.ChatSlowCommand;
import net.kryunek.hub.commands.chat.sub.ChatUnmuteCommand;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.command.CommandSender;

public class ChatCommand extends BaseCommand {

    public ChatCommand() {
        new ChatClearCommand();
        new ChatMuteCommand();
        new ChatUnmuteCommand();
        new ChatSlowCommand();
    }

    @Command(name = "chat", permission = "celest.command.chat", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        sender.sendMessage(CC.translate("&7&m------------------------------------------------"));
        sender.sendMessage(CC.translate("&b&lChat Help"));
        sender.sendMessage(CC.translate(""));
        sender.sendMessage(CC.translate("&b/chat clear &7- &fClear the chat."));
        sender.sendMessage(CC.translate("&b/chat mute &7- &fMute the chat."));
        sender.sendMessage(CC.translate("&b/chat unmute &7- &fUnmute the chat."));
        sender.sendMessage(CC.translate("&b/chat slow <seconds|off> &7- &fSet slow mode."));
        sender.sendMessage(CC.translate("&7&m------------------------------------------------"));
    }
}
