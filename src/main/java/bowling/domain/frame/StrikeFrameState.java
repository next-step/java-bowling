package bowling.domain.frame;

import bowling.domain.Pins;

public class StrikeFrameState implements FrameState {
    private final int offset = 3;

    private StrikeFrameState() {}

    static StrikeFrameState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public FrameEnum getFrameEnum() {
        return FrameEnum.STRIKE;
    }

    @Override
    public int getScore(Frame context, Pins pins) {
        return pins.sum(
                context.getPinsIndex(),
                offset
        );
    }

    @Override
    public boolean hasScore(Frame context, Pins pins) {
        return context.getPinsIndex() + offset <= pins.size();
    }

    @Override
    public void update(Frame context, Pins pins) {}

    private static class SingletonHelper {
        private static final StrikeFrameState instance = new StrikeFrameState();
    }
}
