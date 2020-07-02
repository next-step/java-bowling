package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Frame {

    private final FrameNumber frameNumber;
    private final Pins pins;

    private Frame nextFrame;

    private Frame(FrameNumber frameNumber, Pins pins) {
        this.frameNumber = frameNumber;
        this.pins = pins;
    }

    public static Frame first() {
        return new Frame(FrameNumber.create(0), new NormalPins());
    }

    public Frame last() {
        this.nextFrame = new Frame(FrameNumber.create(9), FinalFramePins.create());
        return this.nextFrame;
    }

    public Frame next() {
        this.nextFrame = new Frame(FrameNumber.create(this.frameNumber.getValue() + 1), new NormalPins());
        return this.nextFrame;
    }

    public void roll(int numberOfDownPin) {
        this.pins.down(numberOfDownPin);
    }

    public boolean hasTurn() {
        return this.pins.hasTurn();
    }

    public Score getScore() {
        if (!isFinished()) {
            return Score.create(0, ScoreType.READY);
        }

        if (isLast() && isFinished()) {
            return Score.create(this.pins.sum(), ScoreType.NORMAL);
        }

        int nextBowlCount = this.pins.getScoreType().getBonusBowlCount();
        List<Integer> downPins = getNextDownPins(nextBowlCount);
        if (downPins.size() < nextBowlCount) {
            return Score.create(0, ScoreType.READY);
        }

        int score = this.pins.sum() + downPins.stream().reduce(0, Integer::sum);
        return Score.create(score, ScoreType.NORMAL);
    }

    public FrameResult getFrameResult() {
        return new FrameResult(this.pins.getDownPins(), this.pins.getScoreType(), getScore());
    }

    private boolean isLast() {
        return this.frameNumber.getValue() == 9;
    }

    private boolean isFinished() {
        return !this.pins.hasTurn();
    }


    private List<Integer> getNextDownPins(int count) {
        if (this.nextFrame == null) {
            return Collections.EMPTY_LIST;
        }

        List<Integer> nextDownPins = this.nextFrame.getDownPins();
        if (nextDownPins.isEmpty()) {
            return new ArrayList<>();
        }

        if (nextDownPins.size() >= count) {
            return nextDownPins.subList(0, count);
        }

        nextDownPins.addAll(this.nextFrame.getNextDownPins(count - nextDownPins.size()));
        return nextDownPins;
    }

    private List<Integer> getDownPins() {
        return this.pins.getDownPins();
    }
}
