package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.*;

import java.util.concurrent.atomic.AtomicInteger;

public class NormalFrame implements Frame {

    private static final int SEMI_FINAL_INDEX = 9;

    private final int round;

    private State state;
    private Frame nextFrame;


    private NormalFrame(int round, int pins) {
        this.round = round;
        this.state = Ready.of(pins);
        this.nextFrame = this.next(0);
    }

    public static NormalFrame bowling(int round, int pins) {
        return new NormalFrame(round, pins);
    }

    @Override
    public Frame bowling(int pins) {
        this.state = this.state.bowling(pins);
        return this;
    }

    @Override
    public Frame next(int pins) {
        if (this.round == SEMI_FINAL_INDEX) {
            this.nextFrame = FinalFrame.lastBowling(pins);

            return this.nextFrame;
        }

        this.nextFrame = bowling(new AtomicInteger(this.round).incrementAndGet(), pins);

        return this.nextFrame;
    }

    @Override
    public int round() {
        return this.round;
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    @Override
    public boolean isFinishBowling() {
        return this.state.is(Strike.class)
                || this.state.is(Spare.class)
                || this.state.is(Miss.class)
                || this.state.is(Gutter.class);
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
        Score currentScore = this.state.createScore();

        if (currentScore.doNotCalculate()) {
            return currentScore.getScore();
        }

        return this.nextFrame.nextScore(currentScore);
    }

    @Override
    public int nextScore(Score before) {
        Score after = this.state.calculateScore(before);

        if (after.doNotCalculate()) {
            return after.getScore();
        }

        return this.nextFrame.nextScore(after);
    }
}
