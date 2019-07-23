package domain.frame;

import domain.Pins;
import domain.Score;
import domain.state.closed.FinalState;
import domain.state.closed.Miss;
import domain.state.open.StandBy;
import domain.state.State;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {
    private static final int INDEX_OF_FINAL_FRAME = 10;
    private static final int MAXIMUM_BOWL_ORDER = 3;
    private static final int INITIAL_BOWL_ORDER = 0;

    private int bowlOrder;
    private List<State> states = new ArrayList<>();

    private FinalFrame() {
        this.bowlOrder = INITIAL_BOWL_ORDER;
        this.states.add(new StandBy());
    }

    public static FinalFrame of() {
        return new FinalFrame();
    }

    @Override
    public Frame fillFrame(Pins fallenPins) {
        if (isGameOver()) {
            throw new GameOverException();
        }
        bowlOrder++;

        if (currentState().isClosed()) {
            this.states.add(new StandBy().update(fallenPins));
            return this;
        }
        State newState = currentState().update(fallenPins);
        states.set(getLastBowlOrder(), newState);

        return this;
    }

    @Override
    public boolean isGameOver() {
        return bowlOrder == MAXIMUM_BOWL_ORDER || isStateMiss();
    }

    private boolean isStateMiss() {
        return states.get(getLastBowlOrder()) instanceof Miss;
    }

    public State currentState() {
        return states.get(getLastBowlOrder());
    }

    private int getLastBowlOrder() {
        return states.size() - 1;
    }

    @Override
    public FrameIndex getIndex() {
        return FrameIndex.from(INDEX_OF_FINAL_FRAME);
    }

    @Override
    public State getState() {
        return new FinalState(states, bowlOrder);
    }

    @Override
    public boolean isSameFrame(Frame targetFrame) {
        return targetFrame.getIndex().equals(getIndex());
    }

    @Override
    public Score getScore() {
        return new FinalState(states, bowlOrder).getScore();
    }

    @Override
    public Score updateScore(Score score) {
        return new FinalState(states, bowlOrder).updateScore(score);
    }

    @Override
    public FrameResult getResult() {
        return FrameResult.of(new FinalState(states, bowlOrder), getScore());
    }

}
