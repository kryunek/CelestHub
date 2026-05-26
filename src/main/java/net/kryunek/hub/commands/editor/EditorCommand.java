package net.kryunek.hub.commands.editor;

import net.kryunek.hub.menus.editor.chat.ChatEditorMenu;
import net.kryunek.hub.menus.editor.hotbar.HotbarEditorMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class EditorCommand extends BaseCommand {

    @Command(name = "editor", aliases = {"editormenu"}, permission = "celest.command.editor")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length < 1) {
            player.sendMessage(CC.translate("&7&m------------------------------"));
            player.sendMessage(CC.translate("&b/editor chat &7- &fOpen chat editor"));
            player.sendMessage(CC.translate("&b/editor hotbar &7- &fOpen hotbar editor"));
            player.sendMessage(CC.translate("&7&m------------------------------"));
            return;
        }

        if (args[0].equalsIgnoreCase("chat")) {
            new ChatEditorMenu().openMenu(player);
            return;
        }

        if (args[0].equalsIgnoreCase("hotbar")) {
            new HotbarEditorMenu().openMenu(player);
            return;
        }

        player.sendMessage(CC.translate("&cUsage: /editor <chat|hotbar>"));
    }
}
