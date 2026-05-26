package net.kryunek.hub.menus.particles.manage.list.editor;

import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.buttons.BackButton;
import net.kryunek.hub.utils.menu.pagination.PageButton;
import net.kryunek.hub.utils.menu.pagination.PaginatedMenu;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TrailParticleEditEffectMenu extends PaginatedMenu {

    private final String trailName;

    public TrailParticleEditEffectMenu(String trailName) {
        this.trailName = trailName;
    }

    @Override
    public String getPrePaginatedTitle(Player player) {
        return CC.translate("&8Edit Trail Effect: " + this.trailName);
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
        int index = 0;
        for (Particle particle : Particle.values()) {
            buttons.put(index++, new TrailParticleEffectSetButton(this.trailName, particle));
        }
        return buttons;
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        buttons.put(getSlot(0, getSize() / 9 - 1), new PageButton(-1, this));
        buttons.put(getSlot(4, getSize() / 9 - 1), new BackButton(new TrailParticleEditorMenu(this.trailName)));
        buttons.put(getSlot(8, getSize() / 9 - 1), new PageButton(1, this));
        return buttons;
    }
}
