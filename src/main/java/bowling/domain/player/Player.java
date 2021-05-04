package bowling.domain.player;

import bowling.domain.frame.Frames;
import bowling.domain.frame.RoundNumber;
import bowling.domain.pin.Pin;

import java.util.Objects;

public final class Player {

    private final PlayerName playerName;
    private final Frames frames;

    private Player(PlayerName playerName) {
        this(playerName, Frames.create());
    }

    public Player(PlayerName playerName, Frames frames) {
        this.playerName = playerName;
        this.frames = frames;
    }

    public static Player from(PlayerName playerName) {
        return new Player(playerName);
    }

    public String playerName() {
        return playerName.value();
    }

    public Frames frames() {
        return frames;
    }

    public boolean isEnded(RoundNumber roundNumber) {
        return frames.isEnded(roundNumber);
    }

    public void knockDownPin(RoundNumber roundNumber, Pin pin) {
        frames.knockDownPin(roundNumber, pin);
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
