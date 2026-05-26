package net.kryunek.hub.utils.menu;

import lombok.Getter;
import lombok.Setter;
import net.kryunek.hub.Celest;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class Menu {

    @Getter
    private static Map<String, Menu> openedMenus = new HashMap<>();

    @Getter
    private Map<Integer, Button> buttons = new HashMap<>();

    private boolean autoUpdate = false;
    private boolean updateAfterClick;
    private boolean closedByMenu;
    private boolean placeholder;
    private BukkitTask task;

    private Button placeholderButton = Button.placeholder(Material.BLACK_STAINED_GLASS_PANE, 0, "", false);


    private ItemStack createItemStack(Player player, Button button) {
        ItemStack item = button.getButtonItem(player);

        if (item.getType() != Material.PLAYER_HEAD) {
            ItemMeta meta = item.getItemMeta();

            if (meta != null && meta.hasDisplayName()) {
                meta.setDisplayName(meta.getDisplayName());
                meta.setLore(meta.getLore());
            }

            item.setItemMeta(meta);
        }

        return item;
    }

    public void openMenu(final Player player) {
        this.buttons = this.getButtons(player);

        Menu previousMenu = openedMenus.get(player.getName());
        Inventory inventory = null;
        String title = this.getTitle(player);
        int size = this.getSize() == -1 ? this.size(this.buttons) : this.getSize();
        boolean update = false;

        if (title.length() > 32) {
            title = title.substring(0, 32);
        }

        if (player.getOpenInventory() != null) {
            if (previousMenu == null) {
                player.closeInventory();
            }
            else {
                int previousSize = player.getOpenInventory().getTopInventory().getSize();

                if (previousSize == size && player.getOpenInventory().getTitle().equals(title)) {
                    inventory = player.getOpenInventory().getTopInventory();
                    update = true;
                }
                else {
                    previousMenu.setClosedByMenu(true);
                    player.closeInventory();
                }
            }
        }

        if (inventory == null) {
            inventory = Bukkit.createInventory(player, size, title);
        }

        inventory.setContents(new ItemStack[inventory.getSize()]);

        openedMenus.put(player.getName(), this);

        for (Map.Entry<Integer, Button> buttonEntry : this.buttons.entrySet()) {
            inventory.setItem(buttonEntry.getKey(), createItemStack(player, buttonEntry.getValue()));
        }

        if (this.isPlaceholder()) {
            Button fillButton = this.getPlaceholderButton() == null ? placeholderButton : this.getPlaceholderButton();

            for (int index = 0; index < size; index++) {
                if (this.buttons.get(index) == null) {
                    this.buttons.put(index, fillButton);
                    inventory.setItem(index, fillButton.getButtonItem(player));
                }
            }
        }

        if (update) {
            player.updateInventory();
        }
        else {
            player.openInventory(inventory);
        }

        this.setClosedByMenu(false);
        if (autoUpdate && task == null) {
            task = Celest.get().getServer().getScheduler().runTaskTimer(Celest.get(), () -> {
                Menu opened = openedMenus.get(player.getName());
                if (opened != this || !player.isOnline()) {
                    if (task != null) {
                        task.cancel();
                        task = null;
                    }
                    return;
                }
                this.openMenu(player);
            }, 10L, 10L);
        }
    }

    public int size(Map<Integer, Button> buttons) {
        int highest = 0;

        for (int buttonValue : buttons.keySet()) {
            if (buttonValue > highest) {
                highest = buttonValue;
            }
        }

        return (int) (Math.ceil((highest + 1) / 9D) * 9D);
    }

    public int getSlot(int x, int y) {
        return ((9 * y) + x);
    }

    public int getSize() {
        return -1;
    }

    public Button getPlaceholderButton() {
        return null;
    }

    public void onClose(Player player) {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }
    public boolean canPlayerClose(Player player) { return true; }

    public abstract String getTitle(Player player);

    public abstract Map<Integer, Button> getButtons(Player player);
}
