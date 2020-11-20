package bowling.domain.frame;

import bowling.domain.pin.FinalFramePins;
import bowling.domain.pin.NormalFramePins;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frame {
    private FrameIndex frameIndex;
    private Pins pins;
    private Frame nextFrame;

    public Frame(FrameIndex frameIndex, Pins pins) {
        this.frameIndex = frameIndex;
        this.pins = pins;
    }

    public static Frame first() {
        return new Frame(FrameIndex.create(0), new NormalFramePins());
    }

    public Frame next() {
        this.nextFrame = new Frame(FrameIndex.create(this.frameIndex.getIndex() + 1), new NormalFramePins());
        return this.nextFrame;
    }

    public Frame last() {
        this.nextFrame = new Frame(FrameIndex.create(9), FinalFramePins.create());
        return this.nextFrame;
    }

    public void roll(int pin) {
        this.pins.down(pin);
    }

    public boolean canRoll() {
        return pins.canRoll();
    }

    public boolean isDone() {
        return !pins.canRoll();
    }

    public FrameResult getFrameResult() {
        return new FrameResult(this.pins.getDownPins(), this.pins.getScoreType(), getScore());
    }

    public Score getScore() {
        if (!isDone()) {
            return Score.of(0, ScoreType.READY);
        }
        if (frameIndex.isLast() && isDone()) {
            return Score.of(this.pins.sum(), ScoreType.READY);
        }
        int nextRollCount = this.pins.getScoreType().getBonusRollCount();
        List<Integer> downPins = getNextDownPins(nextRollCount);

        if (downPins.size() < nextRollCount) {
            return Score.of(0, ScoreType.READY);
        }
        int score =  this.pins.sum() + downPins.stream().mapToInt(Integer::intValue).sum();
        return Score.of(score, ScoreType.NORMAL);
    }

    private List<Integer> getNextDownPins(int count) {
        if (this.nextFrame == null) {
            return Collections.emptyList();
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
