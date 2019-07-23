package domain.state.closed;

import domain.Score;
import domain.state.State;

import java.util.List;
import java.util.stream.Collectors;

import static domain.frame.FrameResult.UNFINISHED_SCORE;

public class FinalState extends Closed {
    private static final String FINAL_FRAME_DELIMITER = "|";
    public static final int BONUS_CHANCE = 3;
    public static final int DEFAULT_CHANCES = 2;
    public static final int FIRST_STATE = 0;

    private List<State> states;
    private int bowlOrder;

    public FinalState(List<State> states, int bowlOrder) {
        this.states = states;
        this.bowlOrder = bowlOrder;
    }

    @Override
    public String printState() {
        return states.stream()
                .map(State::printState)
                .collect(Collectors.joining(FINAL_FRAME_DELIMITER));
    }

    @Override
    public boolean isClosed() { //TODO: 로직 개선하기
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
        return Score.of(UNFINISHED_SCORE, 0);
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
