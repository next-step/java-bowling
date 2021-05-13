package bowling.domain.state;

import bowling.domain.score.Score;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public final class States {

    private static final int SEARCH_START_INDEX = 1;
    private static final String DELIMITER = "|";

    private final LinkedList<State> states;

    private States() {
        this.states = new LinkedList<>(Arrays.asList(State.initialize()));
    }

    public static final States initialize() {
        return new States();
    }

    public final State current() {
        return states.getLast();
    }

    public final States add(final State addition) {
        states.addLast(addition);
        return this;
    }

    public final States remove() {
        states.removeLast();
        return this;
    }

    public final Score score() {
        Score score = states.getFirst().score();
        for (int index = SEARCH_START_INDEX; index < states.size(); index++) {
            State state = states.get(index);
            score = state.calculateAdditionalScore(score);
        }
        return score;
    }

    public final boolean hasBonusRound() {
        return states.stream().anyMatch(State::isAllPinClear);
    }

    public final Score calculateAdditionalScore(final Score beforeScore) {
        Score score = beforeScore;
        for (State state : states) {
            score = state.calculateAdditionalScore(score);
        }
        return score;
    }

    public final String description() {
        return states.stream()
                .map(State::description)
                .collect(Collectors.joining(DELIMITER));
    }
}
