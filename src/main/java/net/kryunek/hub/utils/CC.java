package net.kryunek.hub.utils;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kryunek
 * @since 18/4/2023
 * © 2023 Hub
 **/

public class CC {


    public static final String MENU_BAR;
    public static final String CHAT_BAR;
    public static final String SB_BAR;
    public static final String TAB_BAR;

    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> translate(List<String> in) {
        return in.stream().map(CC::translate).collect(Collectors.toList());

    }

    public static String message(String[] args, int x) {
        StringBuilder builder = new StringBuilder();

        for (int i = x; i < args.length; ++i) {
            builder.append(args[i]);
            builder.append(" ");
        }

        return builder.toString().trim();
    }





    static {

        MENU_BAR = CC.translate("&7&m------------------------");
        CHAT_BAR = CC.translate("&7&m------------------------------------------------");
        SB_BAR = CC.translate("&7&m----------------------");
        TAB_BAR = CC.translate("&7&m-----------------");
    }



}

