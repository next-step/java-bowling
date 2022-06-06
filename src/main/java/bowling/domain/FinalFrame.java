package bowling.domain;

import bowling.domain.state.Miss;
import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.exception.BowlingGameException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {
    private static final int NOT_SCORE = -1;
    private static final int MAX_ROUND = 3;
    private static final String PILE = "|";
    private final List<State> stateList = new ArrayList<>();

    public FinalFrame() {
        this.stateList.add(new Ready());
    }

    @Override
    public Frame bowl(int pins) {
        State state = this.stateList.get(size() - 1);
        if(state.isFinish()) {
            this.stateList.add(new Ready().bowl(pins));
            return this;
        }
        this.stateList.remove(size() - 1);
        this.stateList.add(state.bowl(pins));
        return this;
    }

    @Override
    public Score calculateScore(Score beforeScore) {
        for(State state : stateList) {
            beforeScore = state.calculateScore(beforeScore);
        }
        return beforeScore;
    }

    @Override
    public int score() {
        if(!this.stateList.get(0).isFinish()) {
            return NOT_SCORE;
        }
        try {
            Score score = this.stateList.get(0).getScore();
            for (int i = 1; i < size(); i++) {
                score = this.stateList.get(i).calculateScore(score);
            }
            return score.getScore();
        }catch(BowlingGameException b) {
            return NOT_SCORE;
        }
    }

    @Override
    public String frameExpression() {
        return this.stateList.stream()
                .map(State::expression)
                .collect(Collectors.joining(PILE));
    }

    @Override
    public boolean isFinish() {
        return size() == MAX_ROUND || this.stateList.get(size() -1) instanceof Miss;
    }

    private int size() {
        return this.stateList.size();
    }

}
