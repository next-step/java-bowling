package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class States {
    private final List<State> states;

    private States() {
        states = new ArrayList<>(Collections.singletonList(State.READY));
    }

    public static States init() {
        return new States();
    }

    private int getLastIndex() {
        return states.size() - 1;
    }

    public void add(State state) {
        states.add(state);
    }

    public State getLast() {
        return states.get(getLastIndex());
    }

    public String symbol(Pins pins) {
        return IntStream.range(0, pins.size())
                .mapToObj(i -> states.get(i).symbol(pins.get(i)))
                .collect(joining("|"));
    }

    public void removeLast() {
        states.remove(getLastIndex());
    }

}
