package bowling.controller;

import bowling.domain.frame.Frames;
import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import bowling.domain.player.Player;

import java.util.List;

public class BowlingGame2 {
    private final Player player;
    private final Frames frames;

    public BowlingGame2(final Player player) {
        this.player = player;
        this.frames = Frames.create();
    }

    public void play(final BowlCount bowlCount) {
        Pins pins = Pins.of();
    }

    public Player getCurrentPlayer() {
        return player;
    }

    public List<String> getResult() {
        return frames.getStateResult();
    }

}
