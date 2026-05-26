package net.kryunek.hub.menus.outfit.manage.list.editor;

import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.outfit.Outfit;
import net.kryunek.hub.menus.outfit.manage.list.OutfitPaginatedMenu;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.menu.Button;
import net.kryunek.hub.utils.menu.Menu;
import net.kryunek.hub.utils.menu.buttons.BackButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class OutfitEditorMenu extends Menu {

    private final String outfitName;

    public OutfitEditorMenu(String outfitName) {
        this.outfitName = outfitName;
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate("&7Editing Outfit >> " + this.outfitName);
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        Outfit outfit = ModuleService.getManagerModule().getOutfitManager().getOutfit(this.outfitName);
        if (outfit != null) {
            buttons.put(0, new OutfitDeleteButton(this.outfitName));
            buttons.put(1, new OutfitEditColorButton(this.outfitName));
        }

        buttons.put(8, new BackButton(new OutfitPaginatedMenu()));
        setPlaceholder(false);
        return buttons;
    }
}
