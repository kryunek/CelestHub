package net.kryunek.hub.commands.lottery.sub;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class LotteryCreateCommand extends BaseCommand {

    @Command(name = "lottery.create", aliases = {"loteria.create", "cupon.create", "coupon.create"})
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        Player player = cmdArgs.getPlayer();
        String[] args = cmdArgs.getArgs();
        if (args.length < 2) {
            player.sendMessage(CC.translate("&cUsage: /lottery create <name> <seconds>"));
            return;
        }

        int duration;
        try {
            duration = Integer.parseInt(args[1]);
        } catch (NumberFormatException exception) {
            player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                    .getString("LOTTERY.INVALID_NUMBER", "&cInvalid number.", true)));
            return;
        }

        if (duration <= 0) {
            player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                    .getString("LOTTERY.INVALID_NUMBER", "&cInvalid number.", true)));
            return;
        }

        boolean created = ModuleService.getManagerModule().getLotteryManager().createLottery(args[0], duration);
        if (!created) {
            player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                    .getString("LOTTERY.ALREADY_EXISTS", "&cA lottery with that name already exists.", true)));
            return;
        }

        player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                .getString("LOTTERY.CREATED", "&aLottery created: &f%lottery% &7(%seconds%s)", true)
                .replace("%lottery%", args[0])
                .replace("%seconds%", String.valueOf(duration))));
    }
}
