package net.kryunek.hub.menus.particles.manage.list.editor;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.particles.TrailParticle;
import net.kryunek.hub.menus.particles.manage.list.TrailParticlePaginatedMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.Menu;
import net.kryunek.hub.utils.menu.buttons.BackButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TrailParticleEditorMenu extends Menu {

    private final String trailName;

    public TrailParticleEditorMenu(String trailName) {
        this.trailName = trailName;
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate("&7Editing Trail >> " + this.trailName);
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        TrailParticle trailParticle = ModuleService.getManagerModule().getTrailParticleManager().getTrail(this.trailName);

        if (trailParticle != null) {
            buttons.put(0, new TrailParticleDeleteButton(this.trailName));
            buttons.put(1, new TrailParticleEditEffectButton(this.trailName));
            buttons.put(2, new TrailParticleEditItemButton(this.trailName));
        }

        buttons.put(8, new BackButton(new TrailParticlePaginatedMenu()));
        setPlaceholder(true);
        return buttons;
    }
}
