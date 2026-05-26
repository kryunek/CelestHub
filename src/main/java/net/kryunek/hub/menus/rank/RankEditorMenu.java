package net.kryunek.hub.menus.rank;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.menus.editor.CelestEditorMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.buttons.BackButton;
import net.kryunek.hub.utils.menu.pagination.PageButton;
import net.kryunek.hub.utils.menu.pagination.PaginatedMenu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class RankEditorMenu extends PaginatedMenu {

    {
        setAutoUpdate(true);
    }

    @Override
    public String getPrePaginatedTitle(Player player) {
        return CC.translate("&8Rank Editor");
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
        int index = 8;
        for (String rank : ModuleService.getManagerModule().getRankManager().getAvailableRanks()) {
            buttons.put(index++, new RankEditorButton(rank));
        }
        return buttons;
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        int row = getSize() / 9 - 1;
        buttons.put(getSlot(0, row), new PageButton(-1, this));
        buttons.put(getSlot(4, row), new RankSystemToggleButton());
        buttons.put(getSlot(7, row), new BackButton(new CelestEditorMenu()));
        buttons.put(getSlot(8, row), new PageButton(1, this));
        return buttons;
    }
}
