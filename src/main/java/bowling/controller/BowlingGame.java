package bowling.controller;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.Frames;
import bowling.domain.frame.state.States;
import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import bowling.domain.player.Player;
import bowling.domain.score.Score;

import java.util.List;

public class BowlingGame {
    private final Player player;
    private final Frames frames;

    public BowlingGame(final Player player) {
        this.player = player;
        this.frames = Frames.create();
    }

    public void play(final BowlCount bowlCount) {
        Pins pins = Pins.of();
        frames.bowl(pins.knockOver(bowlCount));
    }

    public Player getCurrentPlayer() {
        return player;
    }

    // todo - 여기서 FrameNumber을 알필요가 있을까?
    public FrameNumber getFrameNumber() {
        return frames.getCurrent()
                     .getFrameNumber();
    }

    public boolean isEnd() {
        Frame current = frames.getCurrent();
        return current.getFrameNumber().isFinal() && current.isEnd();
    }

    public List<States> getStates() {
        return frames.getStates();
    }

    public List<Score> getScores() {
        return frames.getScores();
    }
}
