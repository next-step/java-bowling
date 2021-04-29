package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {
    public final DownPins downPins;
    public final List<State> states = new ArrayList<>();
    public Frame() {
        downPins = new DownPins();
    }

    public DownPins downPins() {
        return downPins;
    }

    public List<State> states() {
        return states;
    }

    public boolean hasResult() {
        return !states.isEmpty();
    }

    abstract public void pitching(int pinCount);
    abstract public boolean isContinue();
    abstract public boolean isEndFrame();

    @Override
    public String toString() {
        return "Frame{" +
                "downPins=" + downPins +
                ", states=" + states +
                '}';
    }
}
