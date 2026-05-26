package net.kryunek.hub.utils.menu.pagination;

import lombok.Getter;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.Menu;
import net.kryunek.hub.utils.menu.buttons.BackButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ViewAllPagesMenu extends Menu {

    @Getter
    private final PaginatedMenu menu;

    public ViewAllPagesMenu(PaginatedMenu menu) {
        this.menu = menu;
    }
    @Override
    public String getTitle(Player player) {
        return "Jump to page";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = new HashMap<>();

        buttons.put(0, new BackButton(menu));

        int index = 10;

        for (int i = 1; i <= menu.getPages(player); i++) {
            buttons.put(index++, new JumpToPageButton(i, menu, menu.getPage() == i));

            if ((index - 8) % 9 == 0) {
                index += 2;
            }
        }

        return buttons;
    }
}