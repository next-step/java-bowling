package domain.frame;

import domain.Pins;
import domain.bowling.Bowling;
import domain.bowling.ReadySet;
import domain.score.Score;
import domain.state.State;
import domain.state.Waiting;

public class NormalFrame implements Frame {

    private Bowling bowling;
    private State state;
    private Frame next;

    public NormalFrame(Frame nextFrame) {
        this.next = nextFrame;
        this.bowling = new ReadySet();
        this.state = new Waiting(Pins.EMPTY);
    }

    @Override
    public Frame setKnockedDownPins(Pins knockedDown) {
        bowling = bowling.bowl(knockedDown);
        state = bowling.getFrameState();
        return this;
    }

    @Override
    public Score getScore() {
        Score score = state.getScore();
        return next.getBonusScore(score);
    }

    @Override
    public Score getBonusScore(Score beforeScore) {
        Score score = state.calculateBonusScore(beforeScore);
        if (score.hasBonus()) {
            return next.getBonusScore(score);
        }
        return score;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public boolean isClosed() {
        return state.isClosed();
    }

}
