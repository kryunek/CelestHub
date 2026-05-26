package net.kryunek.hub.commands.spawn;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class SpawnCommand extends BaseCommand {

    @Command(name = "spawn")
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        Player player = cmdArgs.getPlayer();
        ModuleService.getManagerModule().getSpawnManager().toSpawn(player, true);
        player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages").getString("SPAWN.TELEPORT")));
    }
}
