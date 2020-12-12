package bowling;

import bowling.bowler.Bowler;
import bowling.frame.Frame;
import bowling.frame.Frames;

import java.util.LinkedList;
import java.util.Objects;

public class BowlingGame {

    private final Bowler bowler;
    private final Frames frames;

    private BowlingGame(Bowler bowler, Frames frames) {
        this.bowler = bowler;
        this.frames = frames;
    }

    public static BowlingGame create(Bowler bowler) {
        return new BowlingGame(bowler, Frames.from(new LinkedList<>()));
    }

    public Frame bowl(String fellPins) {
        return frames.bowl(fellPins);
    }

    public boolean isCurrentFrameFinish(int currentFrameNumber) {
        return frames.isCurrentFrameFinish(currentFrameNumber);
    }

    public boolean equalsBowler(Bowler bowler) {
        return this.bowler.equals(bowler);
    }

    public int getFrameNumber() {
        return frames.getFrameNumber();
    }

    public String getBowlerName() {
        return bowler.getName();
    }

    public Frames getFrames() {
        return frames;
    }

    public int getFramesSize() {
        return frames.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame that = (BowlingGame) o;
        return bowler.equals(that.bowler);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bowler);
    }

}
