package net.kryunek.hub.menus.lottery.list.editor;

import lombok.AllArgsConstructor;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.UUID;

@AllArgsConstructor
public class LotteryParticipantEntryButton extends Button {

    private final UUID playerId;
    private final String playerName;

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemBuilder builder = new ItemBuilder(Material.PLAYER_HEAD);
        if (playerId != null) {
            builder.owner(playerId, playerName);
        } else if (playerName != null && !playerName.isBlank()) {
            builder.owner(playerName);
        }

        return builder
                .name(CC.translate("&f" + playerName))
                .lore(Arrays.asList(CC.translate("&7Participant entry")))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
    }
}
