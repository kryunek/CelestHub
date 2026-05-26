package net.kryunek.hub.menus.particles.manage.list.create;

import net.kryunek.hub.managers.particles.TrailParticleCreateSession;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.pagination.PageButton;
import net.kryunek.hub.utils.menu.pagination.PaginatedMenu;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TrailParticleEffectMenu extends PaginatedMenu {

    @Override
    public String getPrePaginatedTitle(Player player) {
        TrailParticleCreateSession.CreationData data = TrailParticleCreateSession.get(player);
        String name = data == null ? "Trail" : data.getTrailName();
        return CC.translate("&8Pick Particle: " + name);
    }

    @Override
    public int getSize() {
        return 36;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        int index = 8;

        for (Particle particle : Particle.values()) {
            buttons.put(index++, new TrailParticleEffectButton(particle));
        }

        return buttons;
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        buttons.put(getSlot(0, getSize() / 9 - 1), new PageButton(-1, this));
        buttons.put(getSlot(4, getSize() / 9 - 1), new TrailParticleCreationInfoButton());
        buttons.put(getSlot(7, getSize() / 9 - 1), new TrailParticleCreationCancelButton());
        buttons.put(getSlot(8, getSize() / 9 - 1), new PageButton(1, this));
        return buttons;
    }
}
