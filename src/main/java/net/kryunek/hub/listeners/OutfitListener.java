package net.kryunek.hub.listeners;

import net.kryunek.hub.Celest;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.outfit.OutfitCreateSession;
import net.kryunek.hub.managers.outfit.OutfitManager;
import net.kryunek.hub.managers.player.Profile;
import net.kryunek.hub.menus.outfit.manage.list.create.OutfitCreateEditorMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OutfitListener implements Listener {

    private final OutfitManager outfitManager;
    private final FileConfig messages;

    public OutfitListener(Celest hub) {
        Bukkit.getPluginManager().registerEvents(this, hub);
        this.outfitManager = ModuleService.getManagerModule().getOutfitManager();
        this.messages = ModuleService.getFileModule().getFile("messages");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (!OutfitCreateSession.isActive(player)) {
            return;
        }

        event.setCancelled(true);
        String message = event.getMessage().trim();
        if (message.equalsIgnoreCase("cancel")) {
            OutfitCreateEditorMenu.restorePreview(player);
            OutfitCreateSession.stop(player);
            player.sendMessage(CC.translate(messages.getString("OUTFIT.CREATE.CANCELLED")));
            return;
        }

        if (message.isEmpty() || message.contains(" ")) {
            player.sendMessage(CC.translate(messages.getString("OUTFIT.CREATE.INVALID_NAME")));
            return;
        }

        if (this.outfitManager.getOutfit(message) != null) {
            player.sendMessage(CC.translate(messages.getString("OUTFIT.CREATE.ALREADY_EXISTS")));
            return;
        }

        Profile profile = ModuleService.getManagerModule().getProfileManager().getProfile(player.getUniqueId());
        OutfitCreateSession.start(player, message, profile.getOutfit() == null ? null : profile.getOutfit().getName());
        Bukkit.getScheduler().runTask(Celest.get(), () -> new OutfitCreateEditorMenu().openMenu(player));
    }
}
