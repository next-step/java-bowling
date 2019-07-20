package domain.frame;

import domain.Pins;
import domain.bowling.Bowling;
import domain.bowling.ReadySet;
import domain.score.Score;
import domain.state.Ready;
import domain.state.State;
import domain.state.Waiting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NormalFrame implements Frame {

    private Bowling bowling;
    private State state;
    private Frame next;

    public NormalFrame(Frame nextFrame) {
        this.next = nextFrame;
        this.bowling = new ReadySet();
        this.state = new Ready();
    }

    @Override
    public Frame setKnockedDownPins(Pins knockedDown) {
        bowling = bowling.bowl(knockedDown);
        state = bowling.getFrameState();
        return this;
    }

    @Override
    public Score getScore() {
        if(state instanceof Waiting) {
            return Score.EMPTY;
        }
        Score score = state.getScore();
        if(score.hasBonus()) {
            return next.getBonusScore(score);
        }
        return score;
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
    public List<State> getState() {
        List<State> list = new ArrayList<>();
        list.add(state);
        return list;
    }

    @Override
    public boolean isClosed() {
        return state.isClosed();
    }

}
