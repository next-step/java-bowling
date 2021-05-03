package bowling.domain;

import java.util.Objects;

public class BowlingGame {
    private final Player player;
    private final Bowling bowling;

    public BowlingGame(Player player) {
        this.player = player;
        this.bowling = new Bowling();
    }

    public BowlingGame(Player player, Frame frame) {
        this.player = player;
        this.bowling = new Bowling(frame);
    }

    public Player player() {
        return player;
    }

    public FrameNumber frameNumber() {
        return bowling.frameNumber();
    }

    public BowlingResult result() {
        return bowling.result(player);
    }

    public void roll(Pinfall pinfall) {
        bowling.roll(pinfall);
    }

    public boolean isDone() {
        return bowling.isDone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame that = (BowlingGame) o;
        return Objects.equals(player, that.player) && Objects.equals(bowling, that.bowling);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, bowling);
    }
}
