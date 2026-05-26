package net.kryunek.hub.menus.outfit.manage.list.create;

import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.buttons.BackButton;
import net.kryunek.hub.utils.menu.pagination.PageButton;
import net.kryunek.hub.utils.menu.pagination.PaginatedMenu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class OutfitColorPaginatedMenu extends PaginatedMenu {

    private static final ColorPreset[] PRESETS = new ColorPreset[]{
            new ColorPreset("Ruby", 255, 0, 0),
            new ColorPreset("Crimson", 200, 20, 60),
            new ColorPreset("Scarlet", 255, 36, 0),
            new ColorPreset("Rose", 255, 90, 120),
            new ColorPreset("Blush", 255, 180, 200),
            new ColorPreset("Coral", 255, 110, 80),
            new ColorPreset("Peach", 255, 184, 108),
            new ColorPreset("Orange", 255, 140, 0),
            new ColorPreset("Amber", 255, 180, 0),
            new ColorPreset("Sun", 255, 220, 0),
            new ColorPreset("Gold", 255, 200, 70),
            new ColorPreset("Sand", 216, 194, 117),
            new ColorPreset("Olive", 128, 128, 0),
            new ColorPreset("Lime", 120, 255, 0),
            new ColorPreset("Apple", 130, 200, 40),
            new ColorPreset("Emerald", 0, 200, 120),
            new ColorPreset("Mint", 120, 255, 180),
            new ColorPreset("Teal", 0, 170, 170),
            new ColorPreset("Aqua", 90, 255, 255),
            new ColorPreset("Cyan", 0, 220, 255),
            new ColorPreset("Ocean", 0, 170, 255),
            new ColorPreset("Sky", 120, 190, 255),
            new ColorPreset("Blue", 0, 90, 255),
            new ColorPreset("Sapphire", 15, 82, 186),
            new ColorPreset("Navy", 20, 40, 140),
            new ColorPreset("Indigo", 75, 0, 130),
            new ColorPreset("Amethyst", 170, 85, 255),
            new ColorPreset("Violet", 120, 60, 255),
            new ColorPreset("Lavender", 200, 162, 255),
            new ColorPreset("Magenta", 255, 0, 180),
            new ColorPreset("Fuchsia", 255, 0, 255),
            new ColorPreset("Pink", 255, 120, 200),
            new ColorPreset("White", 245, 245, 245),
            new ColorPreset("Pearl", 230, 230, 220),
            new ColorPreset("Silver", 180, 180, 180),
            new ColorPreset("Gray", 110, 110, 110),
            new ColorPreset("Slate", 90, 103, 117),
            new ColorPreset("Smoke", 70, 70, 70),
            new ColorPreset("Midnight", 35, 35, 35),
            new ColorPreset("Black", 10, 10, 10)
    };

    {
        setPlaceholder(false);
    }

    @Override
    public String getPrePaginatedTitle(Player player) {
        return CC.translate("&8Outfit Colors");
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public int getMaxItemsPerPage(Player player) {
        return 36;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        int index = 8;
        for (ColorPreset preset : PRESETS) {
            buttons.put(index++, new OutfitColorPresetButton(preset.name, preset.red, preset.green, preset.blue, true));
        }
        return buttons;
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        buttons.put(getSlot(0, 5), new PageButton(-1, this));
        buttons.put(getSlot(4, 5), new BackButton(new OutfitCreateEditorMenu()));
        buttons.put(getSlot(8, 5), new PageButton(1, this));
        return buttons;
    }

    private record ColorPreset(String name, int red, int green, int blue) {
    }
}
