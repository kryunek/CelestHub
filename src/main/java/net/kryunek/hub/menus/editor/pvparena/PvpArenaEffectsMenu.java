package net.kryunek.hub.menus.editor.pvparena;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.buttons.BackButton;
import net.kryunek.hub.utils.menu.pagination.PageButton;
import net.kryunek.hub.utils.menu.pagination.PaginatedMenu;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class PvpArenaEffectsMenu extends PaginatedMenu {

    public PvpArenaEffectsMenu() {
        setAutoUpdate(true);
    }

    @Override
    public String getPrePaginatedTitle(Player player) {
        return CC.translate("&8PvP Arena Effects");
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
        for (PotionEffectType type : PotionEffectType.values()) {
            if (type == null) continue;
            buttons.put(index++, new PvpArenaEffectEntryButton(type));
        }
        return buttons;
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        int row = getSize() / 9 - 1;
        buttons.put(getSlot(0, row), new PageButton(-1, this));
        buttons.put(getSlot(4, row), new BackButton(new PvpArenaEditorMenu()));
        buttons.put(getSlot(8, row), new PageButton(1, this));
        return buttons;
    }
}

