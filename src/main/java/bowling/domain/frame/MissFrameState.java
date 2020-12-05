package bowling.domain.frame;

import bowling.domain.Pins;

public class MissFrameState implements FrameState {
    private MissFrameState() {}

    static MissFrameState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public FrameEnum getFrameEnum() {
        return FrameEnum.MISS;
    }

    @Override
    public int getScore(Frame context, Pins pins) {
        final int offset = 2;
        return pins.sum(
                context.getPinsIndex(),
                offset
        );
    }

    @Override
    public boolean hasScore(Frame context, Pins pins) {
        return true;
    }

    @Override
    public void update(Frame context, Pins pins) {}

    private static class SingletonHelper {
        private static final MissFrameState instance = new MissFrameState();
    }
}
