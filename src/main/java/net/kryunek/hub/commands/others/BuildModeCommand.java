package net.kryunek.hub.commands.others;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.player.Profile;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.FileConfig;
import net.kryunek.hub.utils.PlayerUtil;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class BuildModeCommand extends BaseCommand {

    private final FileConfig settingsMenu = ModuleService.getFileModule().getFile("settings_menu");

    @Command(name = "buildmode", permission = "celest.command.buildmode")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        Profile profile = ModuleService.getManagerModule().getProfileManager().getProfile(player.getUniqueId());

        if (!profile.isBuildModeEnabled()) {
            profile.setBuildModeEnabled(true);
            player.sendMessage(CC.translate(settingsMenu.getString("settings.buildmode-enabled")));
            PlayerUtil.clear(player, true, true);
            player.setGameMode(GameMode.CREATIVE);
            player.setAllowFlight(true);
            player.setFlying(false);
            return;
        }

        profile.setBuildModeEnabled(false);
        player.sendMessage(CC.translate(settingsMenu.getString("settings.buildmode-disabled")));
        ModuleService.getManagerModule().getHotbarManager().setHotbar(player);
        player.setGameMode(GameMode.SURVIVAL);
        ModuleService.getManagerModule().getOutfitManager().applySelectedOutfit(player, profile);
        if (profile.isFlyOnJoin()) {
            PlayerUtil.applyHubFlyState(player, true, false);
        } else {
            PlayerUtil.applyHubFlyState(player, false, false);
        }
    }
}
