package bowling.domain;

import java.util.Collections;
import java.util.LinkedList;

import static java.util.stream.Collectors.joining;

public class FinalFrame implements Frame {
    private static final int MIN_BOWL_COUNT = 2;
    private static final int MAX_BOWL_COUNT = 3;

    private final LinkedList<State> states;
    private final LinkedList<Pin> pins;
    private int count;

    private FinalFrame() {
        this.states = new LinkedList<>(Collections.singletonList(State.READY));
        this.pins = new LinkedList<>(Collections.singletonList(Pin.first()));
        this.count = 0;
    }

    public static FinalFrame init() {
        return new FinalFrame();
    }

    @Override
    public Frame bowl(Pin pin) {
        count++;
        State last = states.getLast();

        if (last.isEnd()) {
            addNewState(pin);
            return this;
        }

        changeLastState(pin, last);
        return this;
    }

    private void addNewState(Pin pin) {
        State ready = State.READY;
        states.add(ready.bowl(Pin.first(), pin));
        pins.add(pin);
    }

    private void changeLastState(Pin pin, State last) {
        Pin lastPin = pins.getLast();
        states.removeLast();
        states.add(last.bowl(lastPin, pin));
        if (count == 1) {
            pins.removeLast();
        }
        pins.add(pin);
    }

    @Override
    public boolean isEnd() {
        if (count == MAX_BOWL_COUNT) {
            return true;
        }
        return count == MIN_BOWL_COUNT && total() < 10;
    }

    private int total() {
        return pins.stream().map(Pin::getFallenPinCount).reduce(Integer::sum).orElse(0);
    }

    @Override
    public int getFrameIndex() {
        return FrameIndex.MAX_INDEX;
    }

    @Override
    public String symbol() {
        if (count < MIN_BOWL_COUNT) {
            return "";
        }
        return states.stream()
                .map(state -> {
                    if(state == State.FIRST) {
                        return pins.getLast().symbol();
                    }
                    int index = states.indexOf(state);
                    return state.symbol(pins.get(index), pins.get(index + 1));
                })
                .collect(joining("|"));
    }

    public int size() {
        return states.size();
    }
}
