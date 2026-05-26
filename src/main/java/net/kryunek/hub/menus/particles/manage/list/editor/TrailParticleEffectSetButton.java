package net.kryunek.hub.menus.particles.manage.list.editor;

import lombok.AllArgsConstructor;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.utils.CC;
import net.kryunek.hub.utils.ItemBuilder;
import net.kryunek.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

@AllArgsConstructor
public class TrailParticleEffectSetButton extends Button {

    private static final Map<Particle, Material> PARTICLE_ICONS = createParticleIcons();
    private final String trailName;
    private final Particle particle;

    @Override
    public ItemStack getButtonItem(Player player) {
        Material iconMaterial = PARTICLE_ICONS.getOrDefault(this.particle, getFallbackIcon(this.particle));

        return new ItemBuilder(iconMaterial)
                .name(CC.translate("&b" + this.particle.name()))
                .lore(Arrays.asList(
                        CC.translate("&7Set effect for &f" + this.trailName),
                        CC.translate("&7Preview Icon: &f" + iconMaterial.name()),
                        "",
                        CC.translate("&eClick to apply")
                ))
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        boolean updated = ModuleService.getManagerModule().getTrailParticleManager()
                .updateTrailEffect(this.trailName, this.particle.name());

        if (!updated) {
            playFail(player);
            player.sendMessage(CC.translate("&cCould not update trail effect."));
            return;
        }

        playSuccess(player);
        player.sendMessage(CC.translate("&aTrail updated: &f" + this.trailName + " &7-> &f" + this.particle.name()));
        new TrailParticleEditorMenu(this.trailName).openMenu(player);
    }

    private static Map<Particle, Material> createParticleIcons() {
        Map<Particle, Material> icons = new EnumMap<>(Particle.class);

        icons.put(Particle.FLAME, Material.BLAZE_POWDER);
        icons.put(Particle.SOUL_FIRE_FLAME, Material.SOUL_TORCH);
        icons.put(Particle.LAVA, Material.LAVA_BUCKET);
        icons.put(Particle.SMOKE, Material.CAMPFIRE);
        icons.put(Particle.LARGE_SMOKE, Material.CAMPFIRE);
        icons.put(Particle.WHITE_SMOKE, Material.WHITE_WOOL);
        icons.put(Particle.CAMPFIRE_COSY_SMOKE, Material.CAMPFIRE);
        icons.put(Particle.CAMPFIRE_SIGNAL_SMOKE, Material.HAY_BLOCK);
        icons.put(Particle.EXPLOSION, Material.TNT);
        icons.put(Particle.EXPLOSION_EMITTER, Material.TNT);
        icons.put(Particle.GUST, Material.FEATHER);
        icons.put(Particle.SMALL_GUST, Material.FEATHER);
        icons.put(Particle.GUST_EMITTER_LARGE, Material.WIND_CHARGE);
        icons.put(Particle.GUST_EMITTER_SMALL, Material.WIND_CHARGE);
        icons.put(Particle.CLOUD, Material.WHITE_WOOL);
        icons.put(Particle.DUST, Material.REDSTONE);
        icons.put(Particle.DUST_COLOR_TRANSITION, Material.RED_DYE);
        icons.put(Particle.DRIPPING_WATER, Material.WATER_BUCKET);
        icons.put(Particle.FALLING_WATER, Material.WATER_BUCKET);
        icons.put(Particle.RAIN, Material.WATER_BUCKET);
        icons.put(Particle.SPLASH, Material.WATER_BUCKET);
        icons.put(Particle.BUBBLE, Material.WATER_BUCKET);
        icons.put(Particle.FISHING, Material.FISHING_ROD);
        icons.put(Particle.DRIPPING_LAVA, Material.LAVA_BUCKET);
        icons.put(Particle.FALLING_LAVA, Material.LAVA_BUCKET);
        icons.put(Particle.LANDING_LAVA, Material.MAGMA_BLOCK);
        icons.put(Particle.DRIPPING_HONEY, Material.HONEY_BOTTLE);
        icons.put(Particle.FALLING_HONEY, Material.HONEYCOMB_BLOCK);
        icons.put(Particle.LANDING_HONEY, Material.HONEY_BLOCK);
        icons.put(Particle.DRIPPING_OBSIDIAN_TEAR, Material.CRYING_OBSIDIAN);
        icons.put(Particle.FALLING_OBSIDIAN_TEAR, Material.CRYING_OBSIDIAN);
        icons.put(Particle.LANDING_OBSIDIAN_TEAR, Material.CRYING_OBSIDIAN);
        icons.put(Particle.DRIPPING_DRIPSTONE_LAVA, Material.POINTED_DRIPSTONE);
        icons.put(Particle.FALLING_DRIPSTONE_LAVA, Material.POINTED_DRIPSTONE);
        icons.put(Particle.DRIPPING_DRIPSTONE_WATER, Material.POINTED_DRIPSTONE);
        icons.put(Particle.FALLING_DRIPSTONE_WATER, Material.POINTED_DRIPSTONE);
        icons.put(Particle.END_ROD, Material.END_ROD);
        icons.put(Particle.PORTAL, Material.OBSIDIAN);
        icons.put(Particle.REVERSE_PORTAL, Material.CRYING_OBSIDIAN);
        icons.put(Particle.DRAGON_BREATH, Material.DRAGON_BREATH);
        icons.put(Particle.ENCHANT, Material.ENCHANTING_TABLE);
        icons.put(Particle.INSTANT_EFFECT, Material.GLISTERING_MELON_SLICE);
        icons.put(Particle.EFFECT, Material.POTION);
        icons.put(Particle.WITCH, Material.POTION);
        icons.put(Particle.HAPPY_VILLAGER, Material.EMERALD);
        icons.put(Particle.ANGRY_VILLAGER, Material.REDSTONE);
        icons.put(Particle.MYCELIUM, Material.MYCELIUM);
        icons.put(Particle.NAUTILUS, Material.NAUTILUS_SHELL);
        icons.put(Particle.COMPOSTER, Material.COMPOSTER);
        icons.put(Particle.HEART, Material.RED_DYE);
        icons.put(Particle.TOTEM_OF_UNDYING, Material.TOTEM_OF_UNDYING);
        icons.put(Particle.UNDERWATER, Material.PRISMARINE_CRYSTALS);
        icons.put(Particle.SQUID_INK, Material.INK_SAC);
        icons.put(Particle.GLOW_SQUID_INK, Material.GLOW_INK_SAC);
        icons.put(Particle.SCRAPE, Material.STONE_AXE);
        icons.put(Particle.WAX_ON, Material.HONEYCOMB);
        icons.put(Particle.WAX_OFF, Material.STONE_AXE);
        icons.put(Particle.ELECTRIC_SPARK, Material.LIGHTNING_ROD);
        icons.put(Particle.SONIC_BOOM, Material.ECHO_SHARD);
        icons.put(Particle.SCULK_SOUL, Material.SCULK);
        icons.put(Particle.SCULK_CHARGE, Material.SCULK_CATALYST);
        icons.put(Particle.SCULK_CHARGE_POP, Material.SCULK_SENSOR);
        icons.put(Particle.CHERRY_LEAVES, Material.CHERRY_LEAVES);
        icons.put(Particle.PALE_OAK_LEAVES, Material.PALE_OAK_LEAVES);
        icons.put(Particle.WHITE_ASH, Material.QUARTZ);
        icons.put(Particle.ASH, Material.COAL);
        icons.put(Particle.CRIT, Material.IRON_SWORD);
        icons.put(Particle.ENCHANTED_HIT, Material.ENCHANTED_BOOK);
        icons.put(Particle.DAMAGE_INDICATOR, Material.IRON_AXE);
        icons.put(Particle.ITEM_SLIME, Material.SLIME_BALL);
        icons.put(Particle.ITEM_COBWEB, Material.COBWEB);
        icons.put(Particle.ITEM_SNOWBALL, Material.SNOWBALL);

        return icons;
    }

    private static Material getFallbackIcon(Particle particle) {
        String name = particle.name();

        if (name.contains("FIRE")) return Material.FIRE_CHARGE;
        if (name.contains("SMOKE")) return Material.CAMPFIRE;
        if (name.contains("WATER") || name.contains("BUBBLE") || name.contains("SPLASH") || name.contains("RAIN")) return Material.WATER_BUCKET;
        if (name.contains("LAVA")) return Material.LAVA_BUCKET;
        if (name.contains("HONEY")) return Material.HONEY_BOTTLE;
        if (name.contains("SOUL")) return Material.SOUL_SAND;
        if (name.contains("SCULK")) return Material.SCULK;
        if (name.contains("HEART")) return Material.RED_DYE;
        if (name.contains("DUST")) return Material.GLOWSTONE_DUST;
        if (name.contains("ASH")) return Material.GRAY_DYE;
        if (name.contains("PORTAL")) return Material.OBSIDIAN;
        if (name.contains("GUST") || name.contains("WIND")) return Material.FEATHER;
        if (name.contains("CHARGE")) return Material.WIND_CHARGE;
        if (name.contains("LEAVES")) return Material.OAK_LEAVES;
        if (name.contains("CRIT") || name.contains("HIT")) return Material.IRON_SWORD;
        if (name.contains("ITEM")) return Material.ITEM_FRAME;

        return Material.NETHER_STAR;
    }
}
