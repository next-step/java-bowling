package bowling;

import bowling.state.FirstBowl;
import bowling.state.Miss;
import bowling.state.Ready;
import bowling.state.Spare;
import bowling.state.State;
import bowling.state.Strike;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

    public static final int FINAL_FRAME_NUMBER = 10;
    public static final int MAX_COUNT_BOWL = 3;

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
        if (beforeState.canAddBonusPins()) {
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
        if (states.size() == MAX_COUNT_BOWL) {
            return true;
        }

        if (getLastState() instanceof Miss) {
            return true;
        }

        if (states.stream().anyMatch(state -> state instanceof Spare) && states.stream().anyMatch(state -> state instanceof Strike)) {
            return true;
        }

        if (states.stream().anyMatch(state -> state instanceof Spare) && states.stream().anyMatch(state -> state instanceof FirstBowl)) {
            return true;
        }

        return false;
    }

    @Override
    public int getScores() {
        if (!isFinished()) {
            return Score.INCALCULABLE_SCORE;
        }

        Score score = states.get(0).score();
        for (int i = 1; i < states.size(); i++) {
            State state = states.get(i);
            score = state.calculateAdditionalScore(score);
        }

        return score.getScore();
    }

    @Override
    public int calculateAdditionalScore(Score beforeScore) {
        try {
            return catchCalculateAdditionalScore(beforeScore, 0);
        } catch (CannotCalculateException | IndexOutOfBoundsException exception) {
            return Score.INCALCULABLE_SCORE;
        }
    }

    private int catchCalculateAdditionalScore(Score beforeScore, int index) {
        State state = states.get(index);
        Score score = state.calculateAdditionalScore(beforeScore);
        if (score.canCalculate()) {
            return score.getScore();
        }
        return catchCalculateAdditionalScore(score, index + 1);
    }

    @Override
    public String getDesc() {
        return states.stream().map(State::getDesc)
            .collect(Collectors.joining("|"));
    }
}
