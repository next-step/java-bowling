package bowling.domain.frame;

import bowling.domain.pin.FinalFramePins;
import bowling.domain.pin.NormalFramePins;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreData;
import bowling.domain.score.ScoreType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frame {
    private static final int BASE_INDEX = 0;
    private static final int LAST_NORMAL_FRAME_INDEX = 9;
    private static final int NEXT_FRAME = 1;

    private FrameIndex frameIndex;
    private Pins pins;
    private Frame nextFrame;

    public Frame(FrameIndex frameIndex, Pins pins) {
        this.frameIndex = frameIndex;
        this.pins = pins;
    }

    public static Frame first() {
        return new Frame(FrameIndex.create(BASE_INDEX), new NormalFramePins());
    }

    public Frame next() {
        this.nextFrame = new Frame(FrameIndex.create(this.frameIndex.getIndex() + NEXT_FRAME), new NormalFramePins());
        return this.nextFrame;
    }

    public Frame last() {
        this.nextFrame = new Frame(FrameIndex.create(LAST_NORMAL_FRAME_INDEX), FinalFramePins.of());
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
        return new FrameResult(this.pins.getDownPins(), new ScoreData(getScore(), this.pins.getScoreType()));
    }

    public Score getScore() {
        if (!isDone()) {
            return Score.of(0, ScoreType.READY);
        }
        if (frameIndex.isLast() && isDone()) {
            return Score.of(this.pins.sum(), ScoreType.NORMAL);
        }
        int nextRollCount = this.pins.getScoreType().getBonusRollCount();
        List<Integer> downPins = getNextFrameDownPins(nextRollCount);

        if (downPins.size() < nextRollCount) {
            return Score.of(0, ScoreType.READY);
        }
        int score = this.pins.sum() + downPins.stream().mapToInt(Integer::intValue).sum();
        return Score.of(score, ScoreType.NORMAL);
    }

    private List<Integer> getNextFrameDownPins(int count) {
        if (this.nextFrame == null) {
            return Collections.emptyList();
        }
        List<Integer> downPins = this.nextFrame.getDownPins();
        if (downPins.isEmpty()) {
            return new ArrayList<>();
        }
        if (downPins.size() >= count) {
            return downPins.subList(0, count);
        }
        downPins.addAll(this.nextFrame.getNextFrameDownPins(count - downPins.size()));
        return downPins;
    }

    private List<Integer> getDownPins() {
        return this.pins.getDownPins();
    }

}
