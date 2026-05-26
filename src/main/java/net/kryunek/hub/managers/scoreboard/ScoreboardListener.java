package net.kryunek.hub.managers.scoreboard;

import lombok.Getter;
import net.kryunek.hub.managers.module.ModuleService;
import net.kryunek.hub.managers.player.Profile;
import net.kryunek.hub.managers.scoreboard.events.ScoreboardBoardCreateEvent;
import net.kryunek.hub.managers.scoreboard.events.ScoreboardBoardDestroyEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@Getter
public class ScoreboardListener implements Listener {

	private final Scoreboard assemble;

	/**
	 * Scoreboard Listener.
	 *
	 * @param assemble instance.
	 */

	public ScoreboardListener(Scoreboard assemble) {
		this.assemble = assemble;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Profile profile = ModuleService.getManagerModule().getProfileManager().getProfile(event.getPlayer().getUniqueId());
		if (profile != null && !profile.isShowScoreboard()) {
			event.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
			return;
		}

		// Call Events if enabled.
		if (assemble.isCallEvents()) {
			ScoreboardBoardCreateEvent createEvent = new ScoreboardBoardCreateEvent(event.getPlayer());

			Bukkit.getPluginManager().callEvent(createEvent);
			if (createEvent.isCancelled()) {
				return;
			}
		}

		getAssemble().getBoards().put(event.getPlayer().getUniqueId(), new ScoreboardBoard(event.getPlayer(), getAssemble()));
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		// Call Events if enabled.
		if (assemble.isCallEvents()) {
			ScoreboardBoardDestroyEvent destroyEvent = new ScoreboardBoardDestroyEvent(event.getPlayer());

			Bukkit.getPluginManager().callEvent(destroyEvent);
			if (destroyEvent.isCancelled()) {
				return;
			}
		}

		getAssemble().getBoards().remove(event.getPlayer().getUniqueId());
		event.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
	}

}
