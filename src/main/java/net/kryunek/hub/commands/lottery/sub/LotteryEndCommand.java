package net.kryunek.hub.commands.lottery.sub;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class LotteryEndCommand extends BaseCommand {

    @Command(name = "lottery.end", aliases = {"loteria.end", "cupon.end", "coupon.end"})
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        Player player = cmdArgs.getPlayer();
        String[] args = cmdArgs.getArgs();
        if (args.length < 1) {
            player.sendMessage(CC.translate("&cUsage: /lottery end <name>"));
            return;
        }

        boolean ended = ModuleService.getManagerModule().getLotteryManager().endLottery(args[0], player.getName());
        if (!ended) {
            player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                    .getString("LOTTERY.END_FAILED", "&cCould not finish that lottery.", true)));
            return;
        }

        player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                .getString("LOTTERY.ENDED", "&eLottery ended: &f%lottery%", true)
                .replace("%lottery%", args[0])));
    }
}
