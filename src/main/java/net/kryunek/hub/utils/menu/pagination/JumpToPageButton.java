package net.kryunek.hub.utils.menu.pagination;

import lombok.AllArgsConstructor;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.FileConfig;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class JumpToPageButton extends Button {

    private final int page;
    private final PaginatedMenu menu;
    private final boolean current;
    private final FileConfig commonMenu = ModuleService.getFileModule().getFile("common_menu");

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemBuilder itemBuilder = new ItemBuilder(Material.valueOf(this.current ? commonMenu.getString("JUMP_TO_PAGE.CURRENT_MATERIAL") : commonMenu.getString("JUMP_TO_PAGE.DEFAULT_MATERIAL")), this.page);
        itemBuilder.name(CC.translate(commonMenu.getString("JUMP_TO_PAGE.NAME").replace("%page%", String.valueOf(this.page))));

        if (this.current) {
            itemBuilder.lore(commonMenu.getStringList("JUMP_TO_PAGE.CURRENT_LORE"));
        }

        return itemBuilder.build();
    }

    @Override
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        this.menu.modPage(player, this.page - this.menu.getPage());
        Button.playNeutral(player);
    }
}
