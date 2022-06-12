package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.CannotCalculateScore;
import bowling.exception.InvalidBoundStateException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class States {

    private static final int LAST_CHANCE = 1;
    private static final int TOTAL_HIT_COUNT_WITH_BONUS = 3;
    private static final int FIRST_STATE_INDEX = 0;
    private static final int SECOND_STATE_INDEX = 1;
    private static final String DELIMITER = "|";

    private final List<State> states;

    private States(List<State> states) {
        this.states = states;
    }

    public static States initialize() {
        List<State> states = new ArrayList<>();
        states.add(StateFactory.initialState());
        return new States(states);
    }

    public void bowl(int hit) {
        if (currentState().isFinish()) {
            State state = StateFactory.nextState(this);
            states.add(state.bowl(hit));
            return;
        }
        renewState(currentState().bowl(hit));
    }

    private State state(int index) {
        if (states.size() < index + 1) {
            throw new InvalidBoundStateException(index);
        }
        return states.get(index);
    }

    private void renewState(State state) {
        states.remove(lastStateIndex());
        states.add(state);
    }

    private State currentState() {
        return state(lastStateIndex());
    }

    private int lastStateIndex() {
        return states.size() - 1;
    }

    public boolean hasLastBonusChance() {
        if (!currentState().isFinish()) {
            throw new IllegalStateException();
        }
        if (!currentState().hasBonusChance()) {
            return false;
        }
        return TOTAL_HIT_COUNT_WITH_BONUS - totalBowlingCount() == LAST_CHANCE;
    }

    public boolean isFinish() {
        return currentState().isFinish() && !remainBonusChance();
    }

    private boolean remainBonusChance() {
        if (states.isEmpty()) {
            return false;
        }
        if (!currentState().hasBonusChance()) {
            return false;
        }
        return totalBowlingCount() < TOTAL_HIT_COUNT_WITH_BONUS;
    }

    private int totalBowlingCount() {
        return states.stream()
                .mapToInt(State::bowlingCount)
                .sum();
    }

    public Score calculate(Score score) {
        Score calculatedScore = state(FIRST_STATE_INDEX).calculateAdditionalScore(score);
        if (calculatedScore.hasAdditionalScoreCount()) {
            return calculateScoreToSecondState(calculatedScore);
        }
        return calculatedScore;
    }

    private Score calculateScoreToSecondState(Score score) {
        try {
            return state(SECOND_STATE_INDEX).calculateAdditionalScore(score);
        } catch (InvalidBoundStateException e) {
            throw new CannotCalculateScore();
        }
    }

    public Score score() {
        return states.stream()
                .map(State::score)
                .reduce(Score::sum)
                .orElseThrow(CannotCalculateScore::new);
    }

    public String description() {
        return states.stream()
                .map(State::description)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        States states1 = (States) o;
        return Objects.equals(states, states1.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(states);
    }
}
