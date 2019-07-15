package com.jaeyeonling.bowling.domain.frame;

import com.jaeyeonling.bowling.domain.Count;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;

import java.util.Objects;

public class FrameScore {

    public static final FrameScore UN_SCORE = FrameScore.of(KnockdownPins.MIN_VALUE, 1);
    public static final FrameScore STRIKE = FrameScore.of(KnockdownPins.MAX_VALUE, 2);
    public static final FrameScore SPARE = FrameScore.of(KnockdownPins.MAX_VALUE, 1);
    public static final FrameScore GUTTER = FrameScore.of(KnockdownPins.MIN_VALUE, 0);

    private final int score;
    private final Count remainingCount;

    private FrameScore(final int score,
                       final Count remainingCount) {
        this.score = score;
        this.remainingCount = remainingCount;
    }

    public static FrameScore of(final int score) {
        return of(score, 0);
    }

    public static FrameScore of(final int score,
                                final int counter) {
        return of(score, Count.of(counter));
    }

    public static FrameScore of(final int score,
                                final Count count) {
        return new FrameScore(score, count);
    }

    public FrameScore sum(final FrameScore other) {
        return of(score + other.score, remainingCount.sum(other.remainingCount));
    }

    public FrameScore calculate(final FrameScore other) {
        return of(score + other.score, remainingCount.down());
    }

    public boolean isComplete() {
        return remainingCount.isDefault();
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FrameScore)) {
            return false;
        }

        final FrameScore that = (FrameScore) o;
        return score == that.score &&
                Objects.equals(remainingCount, that.remainingCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, remainingCount);
    }
}