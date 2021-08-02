package bowling.domain.state;

import bowling.domain.pin.DownedPins;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ComplexState extends State {
    private static final int START_IDX_OF_SUB_STATES = 1;
    private static final int START_IDX_OF_STATES = 0;
    private final Stack<State> states;

    public ComplexState() {
        states = new Stack<>();
        states.add(Preparation.init());
    }

    public static ComplexState init() {
        return new ComplexState();
    }

    @Override
    protected State nextState(DownedPins downedPins) {
        State updatedState = lastState().downPins(downedPins);
        changeLastState(updatedState);

        return this;
    }

    private void changeLastState(State updatedState) {
        states.pop();
        states.add(updatedState);
    }

    public void giveExtraChance() {
        if (lastState().isClean()) {
            states.add(Preparation.init());
        }
    }

    private State lastState() {
        return states.peek();
    }

    @Override
    public Score score() {
        Score score = firstState().score();

        for (State state : subStates()) {
            score = state.addScore(score);
        }

        return score;
    }

    private List<State> subStates() {
        return states.subList(START_IDX_OF_SUB_STATES, states.size());
    }

    private State firstState() {
        return states.get(START_IDX_OF_STATES);
    }

    @Override
    protected Score addBonusScore(Score score) {
        for (State state : states) {
            score = state.addScore(score);
        }

        return score;
    }

    @Override
    public boolean isEnd() {
        return lastState().isMiss();
    }

    @Override
    public List<State> getState() {
        return new ArrayList<>(states);
    }

    @Override
    public List<Integer> getDownedPins() {
        return Collections.emptyList();
    }
}
