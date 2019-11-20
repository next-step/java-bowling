package com.seok2.bowling.frame.domain;

import com.seok2.bowling.state.domain.Spare;
import com.seok2.bowling.state.domain.State;
import com.seok2.bowling.state.domain.Strike;

public class FrameScore implements Observer {

    public static final FrameScore PENDING = new FrameScore(null, Score.ZERO, 3);

    private final ScorePublisher publisher;
    private Score score;
    private int remaining;

    private FrameScore(ScorePublisher publisher, Score score, int remaining) {
        this.publisher = publisher;
        this.score = score;
        this.remaining = remaining;
    }

    private FrameScore(ScorePublisher publisher, State state) {
        this.publisher = publisher;
        this.score = state.getScore();
        this.remaining = state instanceof Strike ? 2 : state instanceof Spare ? 1 : 0;
        if (remaining > 0) {
            publisher.subscribe(this);
        }
    }

    public static FrameScore of(ScorePublisher publisher, State state) {
        return new FrameScore(publisher, state);
    }

    public static FrameScore of(Score score) {
        return new FrameScore(null, score, 0);
    }

    public boolean isCalculated() {
        return remaining == 0;
    }

    public Score getScore() {
        return score;
    }

    @Override
    public void update(Score score) {
        this.remaining--;
        this.score = this.score.add(score);
        if (remaining == 0) {
            this.publisher.unsubscribe(this);
        }
    }


}
