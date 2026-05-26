package net.kryunek.hub.menus.rank;

import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.Menu;
import net.kryunek.hub.utils.menu.buttons.BackButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class RankEntryEditorMenu extends Menu {

    private final String rankName;

    public RankEntryEditorMenu(String rankName) {
        this.rankName = rankName;
    }

    @Override
    public boolean isUpdateAfterClick() {
        return true;
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate("&8Edit Rank: " + rankName);
    }

    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        buttons.put(11, new RankQueuePriorityButton(rankName));
        buttons.put(15, new RankTabPriorityButton(rankName));
        buttons.put(22, new BackButton(new RankEditorMenu()));
        setPlaceholder(true);
        return buttons;
    }
}
