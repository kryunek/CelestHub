package net.kryunek.hub.managers.hotbar;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public class Hotbar {
    private boolean enabled;
    private int slot;
    private String command;
    @Setter(value = AccessLevel.NONE)
    private String name;
    private ItemStack item;


    public boolean isHotbarItem(ItemStack stack) {
        return stack != null && stack.getType() != Material.AIR && stack.hasItemMeta() && stack.getItemMeta().getDisplayName() != null && stack.getItemMeta().getDisplayName().equals(this.item.getItemMeta().getDisplayName());
    }


    public Hotbar(String name) {
        this.name = name;
    }

}
