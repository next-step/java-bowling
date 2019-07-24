package domain.state.closed;

import domain.Score;
import domain.state.State;

import java.util.List;
import java.util.stream.Collectors;

public class FinalState extends Closed {
    private static final String FINAL_FRAME_DELIMITER = "|";
    private static final int BONUS_CHANCE = 3;
    private static final int DEFAULT_CHANCES = 2;
    private static final int FIRST_STATE = 0;

    private List<State> states;
    private int bowlOrder;

    private FinalState(List<State> states, int bowlOrder) {
        this.states = states;
        this.bowlOrder = bowlOrder;
    }

    public static State of(List<State> states, int bowlOrder) {
        return new FinalState(states, bowlOrder);
    }

    @Override
    public String printState() {
        return states.stream()
                .map(State::printState)
                .collect(Collectors.joining(FINAL_FRAME_DELIMITER));
    }

    @Override
    public boolean isClosed() {
        if (states.get(FIRST_STATE) instanceof Strike && bowlOrder == BONUS_CHANCE) {
            return Boolean.TRUE;
        }
        if (bowlOrder == BONUS_CHANCE) {
            return Boolean.TRUE;
        }
        if (states.get(FIRST_STATE) instanceof Strike) {
            return Boolean.FALSE;
        }
        return bowlOrder < DEFAULT_CHANCES;
    }

    @Override
    public Score getScore() {
        if (isClosed()) {
            int sumOfScores = states.stream()
                    .map(State::getScore)
                    .mapToInt(Score::getScore)
                    .sum();

            return Score.of(sumOfScores, 0);
        }
        return Score.ofUnfinished();
    }

    @Override
    public Score updateScore(Score score) {
        for (State state : states) {
            score = state.updateScore(score);
            if (score.isFullyCalculated()) {
                return score;
            }
        }
        return score;
    }
}
