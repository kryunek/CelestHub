package net.kryunek.hub.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.*;

public class ServerUtils {

	public static Player[] getPlayers() {
		return Bukkit.getServer().getOnlinePlayers().toArray(new Player[0]);
	}
	public static Collection<? extends Player> getPlayersCollection() {
		return Bukkit.getServer().getOnlinePlayers();
	}

	public static List<Player> getSortedPlayers() {
		return getSortedPlayers(Comparator.comparing(HumanEntity::getName));
	}

	public static List<Player> getSortedPlayers(Comparator<Player> comparator) {
		ArrayList<Player> players = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
		Collections.sort(players, comparator);

		return players;
	}

}