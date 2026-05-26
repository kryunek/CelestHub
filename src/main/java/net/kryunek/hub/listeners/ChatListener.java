package net.kryunek.hub.listeners;

import net.kryunek.hub.Celest;
import net.kryunek.hub.managers.chat.ChatManager;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final ChatManager chatManager;
    private final FileConfig messages;

    public ChatListener(Celest hub) {
        Bukkit.getPluginManager().registerEvents(this, hub);
        this.chatManager = ModuleService.getManagerModule().getChatManager();
        this.messages = ModuleService.getFileModule().getFile("messages");
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (chatManager.isPaused()
                && !player.hasPermission("celest.chat.bypass.pause")
                && !player.hasPermission("celest.chat.bypass.mute")) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(messages.getString("CHAT.MESSAGES.MUTED", "&cChat is currently muted.", true)));
            return;
        }

        if (chatManager.getSlowSeconds() > 0 && !player.hasPermission("celest.chat.bypass.slow")) {
            long remaining = chatManager.getRemainingSlowSeconds(player.getUniqueId());
            if (remaining > 0) {
                event.setCancelled(true);
                player.sendMessage(CC.translate(messages.getString("CHAT.MESSAGES.SLOWMODE", "&cSlow mode is enabled. Wait &f%time%s&c.", true)
                        .replace("%time%", String.valueOf(remaining))));
                return;
            }
            chatManager.registerMessage(player.getUniqueId());
        }

        event.setFormat(CC.translate("%1$s&7: &f%2$s"));
    }
}
