package bowling.domain;

import bowling.domain.frame.Frames;

public class BowlingTurn {

    private final Player player;
    private final Frames frames;

    private BowlingTurn(String name) {
        this.player = Player.of(name);
        this.frames = Frames.init();
    }

    public static BowlingTurn of(String name) {
        return new BowlingTurn(name);
    }

    public void play(int pinCount) {
        frames.bowl(pinCount);
    }

    public boolean isEndTurn() {
        return frames.isDone();
    }

    public boolean isEndAllTurn() {
        return frames.isAllDone();
    }

    public Player player() {
        return this.player;
    }

    public Frames frames() {
        return this.frames;
    }
}
