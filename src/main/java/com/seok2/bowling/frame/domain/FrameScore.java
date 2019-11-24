package com.seok2.bowling.frame.domain;

import com.seok2.bowling.state.domain.Spare;
import com.seok2.bowling.state.domain.State;
import com.seok2.bowling.state.domain.Strike;

public class FrameScore implements Observer {

    public static final FrameScore PENDING = new FrameScore(null, Score.ZERO, Remaining.ofInfinity());

    private final ScorePublisher publisher;
    private Score score;
    private Remaining remaining;

    private FrameScore(ScorePublisher publisher, Score score, Remaining remaining) {
        this.publisher = publisher;
        this.score = score;
        this.remaining = remaining;
    }

    private FrameScore(ScorePublisher publisher, State state) {
        this.publisher = publisher;
        this.score = state.getScore();
        this.remaining = getRemaining(state);
        if (!remaining.isZero()) {
            publisher.subscribe(this);
        }
    }

    private Remaining getRemaining(State state) {
        if (state instanceof Strike) {
            return Remaining.ofStrike();
        }
        return state instanceof Spare ? Remaining.ofSpare() : Remaining.ofZero();
    }

    public static FrameScore of(ScorePublisher publisher, State state) {
        return new FrameScore(publisher, state);
    }

    public static FrameScore of(Score score) {
        return new FrameScore(null, score, Remaining.ofZero());
    }

    public boolean isCalculated() {
        return remaining.isZero();
    }

    public Score getScore() {
        return score;
    }

    @Override
    public void update(Score score) {
        this.remaining.decrement();
        this.score = this.score.add(score);
        if (isCalculated()) {
            this.publisher.unsubscribe(this);
        }
    }


}
