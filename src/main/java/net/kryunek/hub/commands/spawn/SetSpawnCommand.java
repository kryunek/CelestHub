package net.kryunek.hub.commands.spawn;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class SetSpawnCommand extends BaseCommand {

    @Command(name = "setspawn", permission = "celest.command.setspawn")
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        Player player = cmdArgs.getPlayer();
        ModuleService.getManagerModule().getSpawnManager().setLocation(player.getLocation());
        player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages").getString("SPAWN.SET")));
    }
}
