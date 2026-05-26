package net.kryunek.hub.commands.lottery.sub;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class LotteryRewardCommand extends BaseCommand {

    @Command(name = "lottery.reward", aliases = {"loteria.reward", "cupon.reward", "coupon.reward"})
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        Player player = cmdArgs.getPlayer();
        String[] args = cmdArgs.getArgs();
        if (args.length < 2) {
            player.sendMessage(CC.translate("&cUsage: /lottery reward <name> <command>"));
            return;
        }

        String rewardCommand = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        boolean updated = ModuleService.getManagerModule().getLotteryManager().addReward(args[0], rewardCommand);

        if (!updated) {
            player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                    .getString("LOTTERY.NOT_FOUND", "&cLottery not found.", true)));
            return;
        }

        player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                .getString("LOTTERY.REWARD_ADDED", "&aReward added to &f%lottery%&a.", true)
                .replace("%lottery%", args[0])));
    }
}
