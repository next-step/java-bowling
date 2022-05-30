package bowling.domain;

import bowling.domain.state.*;
import bowling.exception.BowlingGameException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame{
    private static final int NOT_SCORE_STATE = -1;
    private final List<State> states = new ArrayList<>();

    public FinalFrame() {
        this.states.add(new Ready());
    }

    @Override
    public Frame bowl(int countOfPins) {
        State state = states.get(states.size() -1);
        if(state.isFinish() && !(state instanceof Miss)) {
            states.add(new Ready().bowl(countOfPins));
            return this;
        }

        if(state.isFinish() && state instanceof Miss) {
            return this;
        }
        states.remove(statesSize() -1);
        states.add(state.bowl(countOfPins));
        return this;
    }

    @Override
    public State getState() {
        return states.get(statesSize() -1);
    }

    @Override
    public String expression() {
        return states.stream()
                .map(State::expression)
                .collect(Collectors.joining(" | "));
    }

    @Override
    public Score calculateAddScore(Score beforeScore) {
        for(State state : states) {
            beforeScore = state.calculateAddScore(beforeScore);
        }
        return beforeScore;
    }

    @Override
    public FrameScore getFrameScore() {
        if(!this.states.get(0).isFinish()) {
            return new FrameScore(NOT_SCORE_STATE);
        }
        try {
            return new FrameScore(getScore());
        }catch(BowlingGameException b) {
            return new FrameScore(NOT_SCORE_STATE);
        }
    }


    public int getScore() {
        Score score = states.get(0).getScore();
        for(int i = 1; i<statesSize(); i++) {
            score = states.get(i).calculateAddScore(score);
        }
        return score.getScore();
    }


    private int statesSize() {
        return this.states.size();
    }

}
