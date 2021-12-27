package bowling.domain.frame;

import bowling.domain.FrameIndex;
import bowling.domain.Pins;
import bowling.domain.state.Ready;
import bowling.domain.state.ThrowingState;

import java.util.Collections;
import java.util.LinkedList;

import static java.util.stream.Collectors.joining;

public class LastFrame implements Frame {
    private static final String DELIMITER_OF_SCORE = "|";
    private static final int MIN_BOWL = 2;
    private static final int MAX_BOWL = 3;

    private final LinkedList<ThrowingState> states;
    private int count;

    private LastFrame() {
        this.states = new LinkedList<>(Collections.singletonList(Ready.create()));
        this.count = 0;
    }

    public static LastFrame initialize() {
        return new LastFrame();
    }

    @Override
    public Frame bowl(Pins pins) {
        count++;
        ThrowingState recent = recentState();
        if (!recent.isMiss() && recent.isEnd()) {
            addNewStatus(pins);
            return this;
        }
        changeRecentStatus(pins, recent);
        return this;
    }

    @Override
    public boolean isEnd() {
        if (count == MAX_BOWL) {
            return true;
        }
        return count == MIN_BOWL && recentState().isMiss();
    }

    private ThrowingState recentState() {
        return states.getLast();
    }

    @Override
    public int getIndex() {
        return FrameIndex.MAX_INDEX;
    }

    @Override
    public String symbol() {
        return states.stream()
                .map(ThrowingState::symbol)
                .collect(joining(DELIMITER_OF_SCORE));
    }

    int size() {
        return states.size();
    }

    private void addNewStatus(Pins pins) {
        ThrowingState ready = Ready.create();
        states.add(ready.bowl(pins));
    }

    private void changeRecentStatus(Pins pins, ThrowingState recent) {
        states.removeLast();
        states.add(recent.bowl(pins));
    }
}
