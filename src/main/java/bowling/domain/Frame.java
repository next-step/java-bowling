package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    public static final int LAST_FRAME_NUMBER = 10;

    protected final FrameState state;
    protected final List<Pin> pins;

    protected Frame(int number) {
        state = FrameState.from(number);
        pins = new ArrayList() {{
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

    public Frame next() {
        if (isLastFrame()) {
            throw new RuntimeException("다음 프레임이 존재하지 않습니다.");
        }

        int nextFrameNumber = nextFrameNumber();
        return nextFrameNumber == LAST_FRAME_NUMBER ? LastFrame.from() : NormalFrame.of(nextFrameNumber);
    }

    private int nextFrameNumber() {
        return state.getNumber() + 1;
    }

    public boolean isLastFrame() {
        return false;
    }

    public boolean canGoNextFrame() {
        return !isLastFrame() && isFinish();
    }

    @Override
    public String toString() {
        return "Frame{" +
                "state=" + state +
                ", pins=" + pins +
                '}';
    }
}