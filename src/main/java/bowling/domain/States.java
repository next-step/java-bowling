package bowling.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class States {
    private final LinkedList<State> states;

    private States() {
        states = new LinkedList<>(Collections.singletonList(State.READY));
    }

    public static States init() {
        return new States();
    }

    public void add(State state) {
        states.add(state);
    }

    public State getLast() {
        return states.getLast();
    }

    public String symbol(Pins pins) {
        return IntStream.range(0, pins.size())
                .mapToObj(i -> states.get(i).symbol(pins.get(i)))
                .collect(joining("|"));
    }

    public void removeLast() {
        states.removeLast();
    }

}
