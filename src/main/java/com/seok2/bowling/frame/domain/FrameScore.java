package com.seok2.bowling.frame.domain;

import com.seok2.bowling.state.domain.Spare;
import com.seok2.bowling.state.domain.State;
import com.seok2.bowling.state.domain.Strike;

public class FrameScore implements Observer {


    public static final FrameScore PENDING = new FrameScore(null, Score.ZERO, 3);
    public static final int REMAINING_ZERO = 0;
    public static final int REMAINING_ONE = 1;
    public static final int REMAINING_TWO = 2;

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
        this.remaining = getRemaining(state);
        if (remaining > REMAINING_ZERO) {
            publisher.subscribe(this);
        }
    }

    private int getRemaining(State state) {
         if(state instanceof Strike) {
            return REMAINING_TWO;
         }
        return state instanceof Spare ? REMAINING_ONE : REMAINING_ZERO;
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
