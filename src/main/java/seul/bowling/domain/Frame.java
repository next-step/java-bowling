package seul.bowling.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Frame {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    public static final int LAST_FRAME_INDEX = 9;

    private int index;
    private FrameResult result;
    private Pins pins;
    private int bonusPlay;

    private Frame(int index) {
        this.index = index;
        this.result = new FrameResult();
        this.pins = new Pins();
    }

    public void addPins(int clearPin) {
        pins.addPin(clearPin, isBonusPlay());
        result.addScore(clearPin, pins.allClear());

        if (index == LAST_FRAME_INDEX && !isBonusPlay()) {
            this.bonusPlay = result.bonusPlay();
        }

        if (isBonusPlay() && endDefaultPlay()) {
            this.bonusPlay--;
        }
    }

    public void addBonusScore(int bonusScore) {
        result.addBonusScore(bonusScore);
    }

    public void addCumulativeScore(int score) {
        result.addCumulativeScore(score);
    }

    public Frame next() {
        return new Frame(index + ONE);
    }

    public static Frame first() {
        return new Frame(ZERO);
    }

    public boolean endFrame() {
        return result.endJudgmentStatus() && this.bonusPlay == ZERO;
    }

    public boolean endCalculateScore() {
        return result.endScore();
    }

    public boolean availableAddBonusScore() {
        return result.availableAddBonusScore();
    }

    public boolean isLastFame() {
        return index == LAST_FRAME_INDEX;
    }

    public boolean isBonusPlay() {
        return this.bonusPlay > ZERO;
    }

    public int getScore() {
        return this.result.getToTalScore();
    }

    private boolean endDefaultPlay() {
        return pins.endDefaultPlayCount(result.isStrike());
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
