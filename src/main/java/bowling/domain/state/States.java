package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.state.running.Ready;

import java.util.Arrays;
import java.util.LinkedList;

public final class States {

    private static final int SEARCH_START_INDEX = 1;

    private final FinalRound finalRound;
    private final LinkedList<State> states;

    private States() {
        this.finalRound = FinalRound.initialize();
        this.states = new LinkedList<>(Arrays.asList(Ready.initialize()));
    }

    public static final States initialize() {
        return new States();
    }

    public final State last() {
        return states.getLast();
    }

    public void add(final State addition) {
        states.addLast(addition);
    }

    public final void remove() {
        states.removeLast();
    }

    public final Score score() {
        Score score = states.getFirst().score();
        for (int index = SEARCH_START_INDEX; index < states.size(); index++) {
            State state = states.get(index);
            score = state.calculateAdditionalScore(score);
        }
        return score;
    }
}
