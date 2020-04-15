package bowling.controller;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.Frames;
import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import bowling.domain.player.Player;

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

    public FrameNumber getFrameNumber() {
        return frames.getCurrent()
                     .getFrameNumber();
    }

    public List<String> getResult() {
        return frames.getStateResult();
    }

    public boolean isEnd() {
        Frame current = frames.getCurrent();
        return current.getFrameNumber().isFinal() && current.isEnd();
    }

}
