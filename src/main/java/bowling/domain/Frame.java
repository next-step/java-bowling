package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    protected final FrameState state;
    protected final List<Pin> pins;

    protected Frame(int number) {
        this.state = FrameState.from(number);
        this.pins = new ArrayList() {{
            add(Pin.from());
        }};
    }

    public String hit(int count) {
        return state.store(getLastPin().hit(count));
    }

    protected Pin getLastPin() {
        return pins.get(pins.size() - 1);
    }

    public int getNumber() {
        return state.getNumber();
    }

    public boolean isFinish() {
        return getLastPin().isFinish();
    }

    public NormalFrame next() {
        return new NormalFrame(state.getNumber() + 1);
    }

    public boolean isLastFrame() {
        return false;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "state=" + state +
                ", pins=" + pins +
                '}';
    }
}