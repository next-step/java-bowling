package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.state.*;

import java.util.Arrays;
import java.util.LinkedList;

public class EndFrame implements Frame {

    private final LinkedList<State> states = new LinkedList<>(Arrays.asList(Ready.of()));
    private final EndFrameCount count = EndFrameCount.of();

    public static Frame of() {
        return new EndFrame();
    }

    @Override
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

    @Override
    public boolean isEnd() {
        return isClear() || count.isMax();
    }

    @Override
    public boolean isClear() {
        State first = states.getFirst();
        return first instanceof Miss || first instanceof Gutter;
    }

    public LinkedList<State> getStates() {
        return states;
    }
}
