package seul.bowling.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Frame {
    protected static final int ZERO = 0;
    private static final int ONE = 1;
    public static final int LAST_FRAME_INDEX = 9;

    private int index;
    protected FrameResult result;
    protected Pins pins;

    protected Frame(int index) {
        this.index = index;
        this.result = new FrameResult();
        this.pins = new Pins();
    }

    public void addPins(int clearPin) {
        pins.addPin(clearPin, false);
        result.addScore(clearPin, pins.allClear());
    }

    public void addBonusScore(int bonusScore) {
        result.addBonusScore(bonusScore);
    }

    public void addCumulativeScore(int score) {
        result.addCumulativeScore(score);
    }

    public Frame next() {
        int newIndex = index + ONE;
        if (newIndex == LAST_FRAME_INDEX) {
            return new LastFrame(newIndex);
        }

        return new Frame(newIndex);
    }

    public static Frame first() {
        return new Frame(ZERO);
    }

    public boolean endFrame() {
        return result.endJudgmentStatus();
    }

    public boolean endCalculateScore() {
        return result.endScore();
    }

    public boolean availableAddBonusScore() {
        return result.availableAddBonusScore();
    }

    public boolean isLastFame() {
        return false;
    }

    public int getScore() {
        return this.result.getToTalScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return index == frame.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
