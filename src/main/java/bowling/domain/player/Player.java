package bowling.domain.player;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.state.States;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

import java.util.List;
import java.util.Objects;

public class Player {
    private final PlayerName playerName;
    private final Frames frames;

    private Player(final PlayerName playerName, final Frames frames) {
        this.playerName = playerName;
        this.frames = frames;
    }

    public static Player of(final String name) {
        return new Player(PlayerName.of(name), Frames.create());
    }

    public void bowl(final Pins knockOver) {
        frames.bowl(knockOver);
    }

    public void waitNextFrame() {
        frames.changeFrameToNext();
    }

    public boolean isFinish() {
        Frame current = frames.getCurrent();
        return current.isFinish();
    }

    public boolean isGameOver() {
        Frame current = frames.getCurrent();
        return current.getFrameNumber().isFinal() && current.isFinish();
    }

    public String getName() {
        return playerName.toString();
    }

    public List<States> getStates() {
        return frames.getStates();
    }

    public List<Score> getScores() {
        return frames.getScores();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Player player = (Player) o;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }
}
