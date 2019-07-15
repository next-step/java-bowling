package com.jaeyeonling.bowling.domain.frame;

import com.jaeyeonling.bowling.domain.Counter;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;

public class FrameScore {

    public static final FrameScore UN_SCORE = FrameScore.of(KnockdownPins.MIN_VALUE, 1);
    public static final FrameScore STRIKE = FrameScore.of(KnockdownPins.MAX_VALUE, 2);
    public static final FrameScore SPARE = FrameScore.of(KnockdownPins.MAX_VALUE, 1);
    public static final FrameScore GUTTER = FrameScore.of(KnockdownPins.MIN_VALUE, 0);

    private final int score;
    private final Counter counter;

    private FrameScore(final int score,
                       final Counter counter) {
        this.score = score;
        this.counter = counter;
    }

    public static FrameScore of(final int score) {
        return of(score, 0);
    }

    public static FrameScore of(final int score,
                                final int counter) {
        return new FrameScore(score, Counter.of(counter));
    }

    public FrameScore sum(final FrameScore other) {
        return new FrameScore(score + other.score, counter.sum(other.counter));
    }

    public FrameScore calculate(final FrameScore other) {
        return new FrameScore(score + other.score, counter.down());
    }

    public boolean isComplete() {
        return counter.isDefault();
    }

    public int getScore() {
        return score;
    }
}