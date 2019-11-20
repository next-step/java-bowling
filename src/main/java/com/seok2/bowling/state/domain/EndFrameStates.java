package com.seok2.bowling.state.domain;

import com.seok2.bowling.frame.domain.EndFrameCount;
import com.seok2.bowling.frame.domain.FrameScore;
import com.seok2.bowling.frame.domain.Score;
import com.seok2.bowling.pin.domain.Pin;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class EndFrameStates {

    private final LinkedList<State> states = new LinkedList<>(Arrays.asList(Ready.of()));
    private final EndFrameCount count = EndFrameCount.of();

    public void roll(Pin felled) {
        count.increment();
        rollAndSet(felled);
        if (states.getLast().isEnd()) {
            states.add(Ready.of());
        }
    }

    private void rollAndSet(Pin felled) {
        State current = states.getLast();
        states.set(states.indexOf(current), current.roll(felled));
    }

    public boolean isEnd() {
        State first = states.getFirst();
        return first instanceof Miss || first instanceof Gutter || count.isMax();
    }

    public FrameScore getScore() {
        return states.stream()
            .map(State::getScore)
            .reduce(Score::add)
            .map(FrameScore::of)
            .orElseThrow(IllegalAccessError::new);
    }

    public String view() {
        return states.stream()
            .filter(s -> !(s instanceof Ready))
            .map(State::view)
            .collect(Collectors.joining("|"));
    }


}
