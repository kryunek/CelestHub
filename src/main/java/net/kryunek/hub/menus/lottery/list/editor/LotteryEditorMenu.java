package net.kryunek.hub.menus.lottery.list.editor;

import net.kryunek.hub.menus.lottery.list.LotteryPaginatedMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.Menu;
import net.kryunek.hub.utils.menu.buttons.BackButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class LotteryEditorMenu extends Menu {

    private final String lotteryName;

    public LotteryEditorMenu(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate("&8Edit Lottery >> &f" + lotteryName);
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        setAutoUpdate(true);
        Map<Integer, Button> buttons = new HashMap<>();
        buttons.put(0, new LotteryToggleButton(lotteryName));
        buttons.put(1, new LotteryAddRewardButton(lotteryName));
        buttons.put(2, new LotteryClearRewardsButton(lotteryName));
        buttons.put(3, new LotteryDeleteButton(lotteryName));
        buttons.put(4, new LotteryParticipantsButton(lotteryName));
        buttons.put(5, new LotteryRewardsButton(lotteryName));
        buttons.put(6, new LotteryWinnersButton(lotteryName));
        buttons.put(8, new BackButton(new LotteryPaginatedMenu()));
        return buttons;
    }
}
