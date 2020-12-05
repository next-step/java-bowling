package bowling.domain.frame;

import bowling.domain.Pins;

public class SpareFrameState implements FrameState {
    private final int offset = 3;

    private SpareFrameState() {}

    static SpareFrameState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public FrameEnum getFrameEnum() {
        return FrameEnum.SPARE;
    }

    @Override
    public int getScore(Frame frame, Pins pins) {
        return pins.sum(
                frame.getPinsIndex(),
                offset
        );
    }

    @Override
    public boolean hasScore(Frame frame, Pins pins) {
        return frame.getPinsIndex() + offset <= pins.size();
    }

    @Override
    public void update(Frame frame, Pins pins) {}

    private static class SingletonHelper {
        private static final SpareFrameState instance = new SpareFrameState();
    }
}
