package net.kryunek.hub.commands.lottery.sub;

import net.kryunek.hub.managers.lottery.Lottery;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.stream.Collectors;

public class LotteryListCommand extends BaseCommand {

    @Command(name = "lottery.list", aliases = {"loteria.list", "cupon.list", "coupon.list"})
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        Player player = cmdArgs.getPlayer();
        Collection<Lottery> lotteries = ModuleService.getManagerModule().getLotteryManager().getLotteries();

        if (lotteries.isEmpty()) {
            player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                    .getString("LOTTERY.LIST_EMPTY", "&cNo lotteries found.", true)));
            return;
        }

        String list = lotteries.stream()
                .map(lottery -> lottery.getName() + (lottery.isActive() ? " &a(active)" : " &7(inactive)"))
                .collect(Collectors.joining("&7, &f"));

        player.sendMessage(CC.translate(ModuleService.getFileModule().getFile("messages")
                .getString("LOTTERY.LIST", "&dLotteries&7: &f%list%", true)
                .replace("%list%", list)));
    }
}
