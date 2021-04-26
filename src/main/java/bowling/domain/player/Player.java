package bowling.domain.player;

import bowling.domain.frame.Frames;

import java.util.Objects;

public final class Player {

    private final PlayerName playerName;
    private final Frames frames;

    public Player(PlayerName playerName, Frames frames) {
        this.playerName = playerName;
        this.frames = frames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName) && Objects.equals(frames, player.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, frames);
    }
}
