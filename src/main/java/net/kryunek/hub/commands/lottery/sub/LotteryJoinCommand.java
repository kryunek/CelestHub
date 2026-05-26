package net.kryunek.hub.commands.lottery.sub;

import net.kryunek.hub.managers.lottery.LotteryManager;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class LotteryJoinCommand extends BaseCommand {

    @Command(name = "lottery.join", aliases = {"loteria.join", "cupon.join", "coupon.join"})
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        Player player = cmdArgs.getPlayer();
        String[] args = cmdArgs.getArgs();
        if (args.length < 1) {
            player.sendMessage(CC.translate("&cUsage: /lottery join <name>"));
            return;
        }

        LotteryManager manager = ModuleService.getManagerModule().getLotteryManager();
        LotteryManager.JoinResult result = manager.joinLottery(player, args[0]);
        switch (result) {
            case JOINED -> player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                    .getString("LOTTERY.JOINED", "&aYou joined lottery &f%lottery%&a.", true)
                    .replace("%lottery%", args[0])));
            case ALREADY_JOINED -> player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                    .getString("LOTTERY.ALREADY_JOINED", "&eYou are already in this lottery.", true)));
            case NOT_ACTIVE -> player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                    .getString("LOTTERY.NOT_ACTIVE", "&cThis lottery is not active.", true)));
            case NOT_FOUND -> player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                    .getString("LOTTERY.NOT_FOUND", "&cLottery not found.", true)));
        }
    }
}
