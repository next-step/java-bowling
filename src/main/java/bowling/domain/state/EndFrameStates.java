package bowling.domain.state;

import bowling.domain.frame.EndFrameCount;
import bowling.domain.pin.Pin;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class EndFrameStates {

    private final LinkedList<State> states = new LinkedList<>(Arrays.asList(Ready.of()));
    private final EndFrameCount count = EndFrameCount.of();

    public void bowl(Pin felledPin) {
        count.increment();
        bowlAndSet(felledPin);
        if (states.getLast().isEnd()) {
            states.add(Ready.of());
        }
    }

    private void bowlAndSet(Pin felledPin) {
        State current = states.getLast();
        states.set(states.indexOf(current), current.bowl(felledPin));
    }

    public boolean isEnd() {
        State first = states.getFirst();
        return first instanceof Miss || first instanceof Gutter || count.isMax();
    }

    public String view() {
        return states.stream()
                .filter(s -> !(s instanceof Ready))
                .map(State::view)
                .collect(Collectors.joining("|"));
    }
}
