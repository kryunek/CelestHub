package net.kryunek.hub.managers.scoreboard;


import lombok.Getter;
import lombok.Setter;
import net.kryunek.hub.managers.scoreboard.events.ScoreboardBoardCreateEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter @Setter
public class Scoreboard {

	private final JavaPlugin plugin;

	private ScoreboardAdapter adapter;
	private ScoreboardThread thread;
	private ScoreboardListener listeners;
	private ScoreboardStyle assembleStyle = ScoreboardStyle.MODERN;

	private Map<UUID, ScoreboardBoard> boards;

	private long ticks = 2;
	private boolean hook = false, debugMode = true, callEvents = true;

	private final ChatColor[] chatColorCache = ChatColor.values();

	/**
	 * Scoreboard.
	 *
	 * @param plugin instance.
	 * @param adapter that is being provided.
	 */
	public Scoreboard(JavaPlugin plugin, ScoreboardAdapter adapter) {
		if (plugin == null) {
			throw new RuntimeException("Scoreboard can not be instantiated without a plugin instance!");
		}

		this.plugin = plugin;
		this.adapter = adapter;
		this.boards = new ConcurrentHashMap<>();

		this.setup();
	}

	/**
	 * Setup Scoreboard.
	 */
	public void setup() {
		// Register Events.
		this.listeners = new ScoreboardListener(this);
		this.plugin.getServer().getPluginManager().registerEvents(listeners, this.plugin);

		// Ensure that the thread has stopped running.
		if (this.thread != null) {
			this.thread.stop();
			this.thread = null;
		}

		// Register new boards for existing online players.
		for (Player player : this.getPlugin().getServer().getOnlinePlayers()) {

			// Call Events if enabled.
			if (this.isCallEvents()) {
				ScoreboardBoardCreateEvent createEvent = new ScoreboardBoardCreateEvent(player);

				Bukkit.getPluginManager().callEvent(createEvent);
				if (createEvent.isCancelled()) {
					continue;
				}
			}

			getBoards().putIfAbsent(player.getUniqueId(), new ScoreboardBoard(player, this));
		}

		// Start Thread.
		this.thread = new ScoreboardThread(this);
	}

	/**
	 * Cleanup Scoreboard.
	 */
	public void cleanup() {
		// Stop thread.
		if (this.thread != null) {
			this.thread.stop();
			this.thread = null;
		}

		// Unregister listeners.
		if (listeners != null) {
			HandlerList.unregisterAll(listeners);
			listeners = null;
		}

		// Destroy player scoreboards.
		for (UUID uuid : getBoards().keySet()) {
			Player player = Bukkit.getPlayer(uuid);

			if (player == null || !player.isOnline()) {
				continue;
			}

			getBoards().remove(uuid);
			player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		}
	}

	public void showBoard(Player player) {
		if (player == null || !player.isOnline()) {
			return;
		}

		getBoards().put(player.getUniqueId(), new ScoreboardBoard(player, this));
	}

	public void hideBoard(Player player) {
		if (player == null) {
			return;
		}

		getBoards().remove(player.getUniqueId());
		player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
	}

}
