package bowling.domain.controller;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.NormalFrame;
import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import bowling.domain.player.Player;

public class BowlingGame {
    private static final String SEPARATOR = ",";

    private final Player player;
    private Frame first;
    private Frame current;

    public BowlingGame(final Player player) {
        this.player = player;
        this.first = NormalFrame.ofFirst();
        this.current = first;
    }

    public void play(final BowlCount bowlCount) {
        Pins pins = Pins.of();
        current = current.bowl(pins.knockOver(bowlCount));
    }

    public boolean isEnd() {
        return current.getFrameNumber().isFinal() && current.isEnd();
    }

    public FrameNumber getFrameNumber() {
        return current.getFrameNumber();
    }

    public String overHead() {
        return player.name() + SEPARATOR + first.getStates();
    }
}
