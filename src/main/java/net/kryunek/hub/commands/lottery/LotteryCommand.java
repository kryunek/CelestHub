package net.kryunek.hub.commands.lottery;

import net.kryunek.hub.commands.lottery.sub.LotteryCreateCommand;
import net.kryunek.hub.commands.lottery.sub.LotteryEndCommand;
import net.kryunek.hub.commands.lottery.sub.LotteryJoinCommand;
import net.kryunek.hub.commands.lottery.sub.LotteryListCommand;
import net.kryunek.hub.commands.lottery.sub.LotteryManagerCommand;
import net.kryunek.hub.commands.lottery.sub.LotteryRewardCommand;
import net.kryunek.hub.commands.lottery.sub.LotteryStartCommand;
import net.kryunek.hub.commands.lottery.sub.LotteryWinnersCommand;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.command.BaseCommand;
import net.kryunek.hub.utils.command.Command;
import net.kryunek.hub.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class LotteryCommand extends BaseCommand {

    @Command(name = "lottery", aliases = {"loteria", "cupon", "coupon"})
    @Override
    public void onCommand(CommandArgs cmdArgs) {
        Player player = cmdArgs.getPlayer();
        String label = cmdArgs.getLabel();
        player.sendMessage(CC.translate("&7&m------------------------------------------------"));
        player.sendMessage(CC.translate("&d&lLottery Help"));
        player.sendMessage(CC.translate(""));
        player.sendMessage(CC.translate("&d/" + label + " join <lottery> &7- &fJoin active lottery."));
        player.sendMessage(CC.translate("&d/" + label + " list &7- &fSee all lotteries."));
        player.sendMessage(CC.translate("&d/" + label + " create <name> <seconds> &7- &fCreate lottery."));
        player.sendMessage(CC.translate("&d/" + label + " reward <name> <command> &7- &fAdd reward command."));
        player.sendMessage(CC.translate("&d/" + label + " winners <name> <count> &7- &fSet winners count."));
        player.sendMessage(CC.translate("&d/" + label + " start <name> &7- &fStart lottery."));
        player.sendMessage(CC.translate("&d/" + label + " end <name> &7- &fFinish and pick winner."));
        player.sendMessage(CC.translate("&d/" + label + " manager &7- &fManage with menus."));
        player.sendMessage(CC.translate("&7&m------------------------------------------------"));
    }

    public LotteryCommand() {
        new LotteryJoinCommand();
        new LotteryListCommand();
        new LotteryCreateCommand();
        new LotteryRewardCommand();
        new LotteryStartCommand();
        new LotteryEndCommand();
        new LotteryWinnersCommand();
        new LotteryManagerCommand();
    }
}
