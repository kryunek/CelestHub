package net.kryunek.hub.managers.lottery;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class Lottery {

    private final String name;
    private int durationSeconds;
    private final List<String> rewards;
    private final Set<UUID> participants;
    private int winnersCount;
    private boolean active;
    private long endAt;

    public Lottery(String name, int durationSeconds, int winnersCount, List<String> rewards) {
        this.name = name;
        this.durationSeconds = Math.max(1, durationSeconds);
        this.winnersCount = Math.max(1, winnersCount);
        this.rewards = rewards == null ? new ArrayList<>() : new ArrayList<>(rewards);
        this.participants = new LinkedHashSet<>();
        this.active = false;
        this.endAt = 0L;
    }

    public boolean addParticipant(UUID uuid) {
        return participants.add(uuid);
    }

    public void removeParticipant(UUID uuid) {
        participants.remove(uuid);
    }

    public int getParticipantCount() {
        return participants.size();
    }

    public long getRemainingSeconds() {
        if (!active) {
            return 0L;
        }
        long remainingMillis = Math.max(0L, endAt - System.currentTimeMillis());
        return (long) Math.ceil(remainingMillis / 1000.0D);
    }
}
