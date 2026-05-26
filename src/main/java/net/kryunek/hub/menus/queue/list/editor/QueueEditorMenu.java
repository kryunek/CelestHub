package net.kryunek.hub.menus.queue.list.editor;

import net.kryunek.hub.menus.editor.CelestEditorMenu;
import net.kryunek.hub.menus.queue.list.QueuePaginatedMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.Menu;
import net.kryunek.hub.utils.menu.buttons.BackButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class QueueEditorMenu extends Menu {

    private final String server;


    @Override
    public int getSize() {
        return 9;
    }

    public QueueEditorMenu(String server) {
        this.server = server;
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate("&7Editing Queue »" + server);
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        setAutoUpdate(true);
        setUpdateAfterClick(true);

        Map<Integer, Button> buttons = new HashMap<>();


        buttons.put(0, new QueuePauseButton(server));


        buttons.put(1, new QueueClearButton(server));

        buttons.put(2, new QueueSendFirstButton(server));

        buttons.put(3, new QueueDeleteButton(server));

        buttons.put(8, new BackButton(new QueuePaginatedMenu()));

        return buttons;
    }
}
