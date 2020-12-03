package bowling.domain.frame;

import bowling.domain.Rolls;

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
    public int getScore(Frame context, Rolls rolls) {
        final int offset = 2;
        return rolls.sum(
                context.getRollIndex(),
                offset
        );
    }

    @Override
    public boolean hasScore(Frame context, Rolls rolls) {
        return true;
    }

    @Override
    public void update(Frame context, Rolls rolls) {}

    private static class SingletonHelper {
        private static final MissFrameState instance = new MissFrameState();
    }
}
