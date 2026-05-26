package net.kryunek.hub.commands.lottery.sub;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class LotteryWinnersCommand extends BaseCommand {

    @Command(name = "lottery.winners", aliases = {"loteria.winners", "cupon.winners", "coupon.winners"})
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        Player player = cmdArgs.getPlayer();
        String[] args = cmdArgs.getArgs();
        if (args.length < 2) {
            player.sendMessage(CC.translate("&cUsage: /lottery winners <name> <count>"));
            return;
        }

        int winners;
        try {
            winners = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex) {
            player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                    .getString("LOTTERY.INVALID_NUMBER", "&cType a valid positive number.", true)));
            return;
        }

        if (winners < 1) {
            player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                    .getString("LOTTERY.INVALID_NUMBER", "&cType a valid positive number.", true)));
            return;
        }

        boolean updated = ModuleService.getManagerModule().getLotteryManager().updateWinnersCount(args[0], winners);
        if (!updated) {
            player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                    .getString("LOTTERY.NOT_FOUND", "&cLottery not found.", true)));
            return;
        }

        player.sendMessage(CC.translate("&aWinners count for &f" + args[0] + "&a updated to &f" + winners + "&a."));
    }
}
