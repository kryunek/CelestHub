package net.kryunek.hub.menus.lottery.list.editor;

import net.kryunek.hub.managers.lottery.Lottery;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.buttons.BackButton;
import net.kryunek.hub.utils.menu.pagination.PageButton;
import net.kryunek.hub.utils.menu.pagination.PaginatedMenu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class LotteryRewardsMenu extends PaginatedMenu {

    private final String lotteryName;

    public LotteryRewardsMenu(String lotteryName) {
        this.lotteryName = lotteryName;
        setAutoUpdate(true);
    }

    @Override
    public String getPrePaginatedTitle(Player player) {
        return CC.translate("&8Rewards >> &f" + lotteryName);
    }

    @Override
    public int getSize() {
        return 36;
    }

    @Override
    public int getMaxItemsPerPage(Player player) {
        return 18;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        Lottery lottery = ModuleService.getManagerModule().getLotteryManager().getLottery(lotteryName);
        if (lottery == null) {
            return buttons;
        }

        int index = 8;
        for (int i = 0; i < lottery.getRewards().size(); i++) {
            String reward = lottery.getRewards().get(i);
            buttons.put(index++, new LotteryRewardEntryButton(lotteryName, i, reward));
        }
        return buttons;
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        int row = getSize() / 9 - 1;
        buttons.put(getSlot(0, row), new PageButton(-1, this));
        buttons.put(getSlot(4, row), new BackButton(new LotteryEditorMenu(lotteryName)));
        buttons.put(getSlot(8, row), new PageButton(1, this));
        return buttons;
    }
}

