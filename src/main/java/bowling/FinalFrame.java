package bowling;

import bowling.state.Ready;
import bowling.state.Spare;
import bowling.state.State;
import bowling.state.Strike;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

    public static final int FINAL_FRAME_NUMBER = 10;

    private final List<State> states = new ArrayList<>();

    private FinalFrame(State state) {
        states.add(state);
    }

    public static FinalFrame start() {
        return new FinalFrame(new Ready());
    }

    @Override
    public Frame bowl(Pin falledPins) {
        if (isFinished()) {
            return this;
        }
        State state = getLastState();
        if (state.isFinished()) {
            State bonus = bonusPins(state, falledPins);
            states.add(bonus);
            return this;
        }
        states.remove(state);
        states.add(state.bowl(falledPins));
        return this;
    }

    private State bonusPins(State beforeState, Pin falledPins) {
        if (beforeState instanceof Strike || beforeState instanceof Spare) {
            return new Ready().bowl(falledPins);
        }
        throw new IllegalStateException("게임이 끝나서 더이상 진행할 수 없습니다.");
    }

    private State getLastState() {
        return states.get(states.size() - 1);
    }

    @Override
    public Frame nextFrame() {
        throw new IllegalStateException("마지막 프레임입니다.");
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public boolean isFinished() {
        if (getLastState().isFinished() && getScores().canCalculate()) {
            return true;
        }
        if (states.size() == 3) {
            return true;
        }
        if (states.size() == 2) {
            return states.stream().anyMatch(state -> state instanceof Spare);
        }

        return false;
    }

    @Override
    public Score getScores() {
        Score score = states.get(0).score();
        for (int i = 1; i < states.size(); i++) {
            State state = states.get(i);
            score = state.calculateAdditionalScore(score);
        }
        return score;
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = beforeScore;
        for (State state : states) {
            score = state.calculateAdditionalScore(score);
        }
        return score;
    }

    @Override
    public String getDesc() {
        return states.stream().map(i -> i.getDesc())
            .collect(Collectors.joining("|"));
    }
}
