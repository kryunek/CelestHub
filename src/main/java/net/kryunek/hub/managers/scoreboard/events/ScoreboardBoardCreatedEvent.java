package net.kryunek.hub.managers.scoreboard.events;


import lombok.Getter;
import lombok.Setter;
import net.kryunek.hub.managers.scoreboard.ScoreboardBoard;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
@Setter
public class ScoreboardBoardCreatedEvent extends Event {

    @Getter
    public static HandlerList handlerList = new HandlerList();

    private boolean cancelled = false;
    private final ScoreboardBoard board;

    /**
     * Scoreboard Board Created Event.
     *
     * @param board of player.
     */
    public ScoreboardBoardCreatedEvent(ScoreboardBoard board) {
        this.board = board;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
