package bowling.domain.state;

import bowling.domain.state.running.Ready;

import java.util.Arrays;
import java.util.LinkedList;

public final class States {

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
}
