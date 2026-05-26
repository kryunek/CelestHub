package net.kryunek.hub.managers.timer;

import lombok.Getter;
import lombok.Setter;
import net.kryunek.hub.utils.TimeUtil;

@Getter
@Setter
public class Timer {

    private final String name; // 🔗 nombre = queue
    private long endTime;

    private boolean paused;
    private long remainingTime;

    private String prefix; // 🎨 NUEVO (para scoreboard)

    public Timer(String name, long durationInSeconds, String prefix) {
        this.name = name;
        this.endTime = System.currentTimeMillis() + (durationInSeconds * 1000);
        this.paused = false;
        this.remainingTime = 0L;
        this.prefix = prefix;
    }

    public Timer(String name, long endTime, boolean paused, long remainingTime, String prefix) {
        this.name = name;
        this.endTime = endTime;
        this.paused = paused;
        this.remainingTime = remainingTime;
        this.prefix = prefix;
    }

    public void pause() {
        if (paused) return;

        this.remainingTime = endTime - System.currentTimeMillis();
        this.paused = true;
    }

    public void resume() {
        if (!paused) return;

        this.endTime = System.currentTimeMillis() + remainingTime;
        this.paused = false;
    }

    public void togglePause() {
        if (paused) {
            resume();
        } else {
            pause();
        }
    }

    public long getRemainingTimeInMillis() {
        if (paused) {
            return Math.max(remainingTime, 0);
        }

        long remaining = endTime - System.currentTimeMillis();
        return Math.max(remaining, 0);
    }

    public boolean isExpired() {
        return !paused && System.currentTimeMillis() >= endTime;
    }

    public String getFormattedTime() {
        return TimeUtil.millisToTimer(getRemainingTimeInMillis());
    }
}
