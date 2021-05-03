package bowling.domain.player;

import java.util.List;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.Score;
import bowling.domain.state.States;

public class Player {
    private final PlayerName playerName;
    private final Frames frames;

    public static Player of(String playerName) {
        return new Player(playerName);
    }

    private Player(String playerName) {
        this.playerName = PlayerName.of(playerName);
        this.frames = Frames.init();
    }

    public void bowl(int bowlingPin) {
        frames.bowl(bowlingPin);
    }

    public String name() {
        return playerName.toString();
    }

    public List<States> states() {
        return frames.states();
    }

    public List<Score> scores() {
        return frames.scores();
    }

    public boolean isDone() {
        Frame current = frames.currentFrame();
        return current.isFinalFrame() && current.isDone();
    }

    public boolean isTurnDone() {
        Frame current = frames.currentFrame();
        return current.isDone();
    }

    public int currentFrameNumber() {
        return frames.frames().size();
    }

    public void changeNextFrame() {
        frames.changePointer();
    }
}
