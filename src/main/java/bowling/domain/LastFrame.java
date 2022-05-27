package bowling.domain;

import static java.util.stream.Collectors.joining;

import java.util.Collections;
import java.util.LinkedList;

public class LastFrame implements Frame {
    private static final String DELIMITER_OF_SCORE = "|";
    private static final int MIN_BOWL = 2;
    private static final int MAX_BOWL = 3;

    private final LinkedList<State> states;
    private int count;

    public LastFrame() {
        this.states = new LinkedList<>(Collections.singletonList(Ready.create()));
        this.count = 0;
    }

    @Override
    public Frame bowl(Pitching pitching) {
        count++;

        State lastState = states.getLast();

        if (lastState.isEnd()) {
            addNewStatus(pitching);
            return this;
        }
        changeLastStatus(pitching, lastState);
        return this;
    }

    private void addNewStatus(Pitching pitching) {
        State ready = Ready.create();
        states.add(ready.bowl(pitching));
    }

    private void changeLastStatus(Pitching pitching, State lastState) {
        states.removeLast();
        states.add(lastState.bowl(pitching));
    }

    @Override
    public boolean isEnd() {
        if (count == MAX_BOWL) {
            return true;
        }
        return count == MIN_BOWL && recentState() instanceof Miss;
    }

    private State recentState() {
        return states.getLast();
    }

    @Override
    public int getFrameNumber() {
        return FrameNumber.MAX_FRAME_NUMBER;
    }

    @Override
    public String toString() {
        return states.stream()
                .map(State::symbol)
                .collect(joining(DELIMITER_OF_SCORE));
    }
}
