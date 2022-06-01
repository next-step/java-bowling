package bowling.domain.frame;

import bowling.domain.frame.exception.UnableBowlingException;
import bowling.domain.frame.exception.UnableCreateFrameException;
import bowling.domain.score.Score;
import bowling.domain.state.*;

import java.util.concurrent.atomic.AtomicInteger;

public class FinalFrame implements Frame {

    private static final int FINAL_ROUND = 9;
    private static final int LIMIT_COUNT = 2;
    private static final int MINIMUM_COUNT = 0;

    private final AtomicInteger count;
    private State state;

    private FinalFrame(int pins) {
        this.count = new AtomicInteger();
        this.state = Ready.of(pins);
    }

    protected static FinalFrame lastBowling(int pins) {
        return new FinalFrame(pins);
    }

    @Override
    public Frame bowling(int pins) {
        if (this.isFinishBowling()) {
            throw new UnableBowlingException();
        }

        count.incrementAndGet();
        if (this.state.is(Strike.class) || this.state.is(Spare.class) || this.state.is(Bonus.class)) {
            this.state = this.state.bonusBowling(pins);
            return this;
        }

        this.state = this.state.bowling(pins);

        return this;
    }

    @Override
    public Frame next(int pins) {
        if (!this.isFinishBowling()) {
            return this.bowling(pins);
        }

        throw new UnableCreateFrameException();
    }

    @Override
    public int round() {
        return FINAL_ROUND;
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public boolean isFinishBowling() {
        return this.state.is(Miss.class)
                || this.state.is(Gutter.class)
                || (this.count.get() == LIMIT_COUNT && this.state.is(Bonus.class));
    }

    @Override
    public boolean isPrinting() {
        return this.isFinishBowling();
    }

    @Override
    public String result() {
        return this.state.symbol();
    }

    @Override
    public State state() {
        return this.state;
    }

    @Override
    public int score() {
        return this.state.totalScore();
    }

    @Override
    public int nextScore(Score before) {

        if (before.left() == LIMIT_COUNT && this.count.get() == MINIMUM_COUNT) {
            before = before.nextScore(MINIMUM_COUNT);
        }

        Score after = this.state.calculateScore(before);

        if (after.doNotCalculate()) {
            return after.getScore();
        }

        return this.nextScore(after);
    }
}
