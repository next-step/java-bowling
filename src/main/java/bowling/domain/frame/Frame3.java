package bowling.domain.frame;

import bowling.domain.pin.FinalFramePins;
import bowling.domain.pin.NormalFramePins;
import bowling.domain.pin.Pins2;
import bowling.domain.score.Score3;
import bowling.domain.score.ScoreType2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frame3 {
    private FrameIndex frameIndex;
    private Pins2 pins2;
    private Frame3 nextFrame;

    public Frame3(FrameIndex frameIndex, Pins2 pins2) {
        this.frameIndex = frameIndex;
        this.pins2 = pins2;
    }

    public static Frame3 first() {
        return new Frame3(FrameIndex.create(0), new NormalFramePins());
    }

    public Frame3 next() {
        this.nextFrame = new Frame3(FrameIndex.create(this.frameIndex.getIndex() + 1), new NormalFramePins());
        return this.nextFrame;
    }

    public Frame3 last() {
        this.nextFrame = new Frame3(FrameIndex.create(9), FinalFramePins.create());
        return this.nextFrame;
    }

    public void roll(int pin) {
        this.pins2.down(pin);
    }

    public boolean canRoll() {
        return pins2.canRoll();
    }

    public boolean isDone() {
        return !pins2.canRoll();
    }

    public FrameResult getFrameResult() {
        return new FrameResult(this.pins2.getDownPins(), this.pins2.getScoreType(), getScore());
    }

    public Score3 getScore() {
        if (!isDone()) {
            return Score3.of(0, ScoreType2.READY);
        }
        if (frameIndex.isLast() && isDone()) {
            return Score3.of(this.pins2.sum(), ScoreType2.READY);
        }
        int nextRollCount = this.pins2.getScoreType().getBonusRollCount();
        List<Integer> downPins = getNextDownPins(nextRollCount);

        if (downPins.size() < nextRollCount) {
            return Score3.of(0, ScoreType2.READY);
        }
        int score =  this.pins2.sum() + downPins.stream().mapToInt(Integer::intValue).sum();
        return Score3.of(score, ScoreType2.NORMAL);
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
        return this.pins2.getDownPins();
    }

}
