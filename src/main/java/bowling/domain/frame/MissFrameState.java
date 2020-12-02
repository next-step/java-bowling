package bowling.domain.frame;

import bowling.domain.FrameEnum;
import bowling.domain.Rolls;

public class MissFrameState implements FrameState {
    private final int offset = 2;

    private MissFrameState() {}

    static MissFrameState getInstance() {
        return SingletonHelper.instance;
    }

    @Override
    public FrameEnum getFrameEnum() {
        return FrameEnum.MISS;
    }

    @Override
    public int getScore(FrameContext context, Rolls rolls) {
        return rolls.sum(
                context.getRollIndex(),
                offset
        );
    }

    @Override
    public boolean hasScore(FrameContext context, Rolls rolls) {
        return true;
    }

    @Override
    public void update(FrameContext context, Rolls rolls) {}

    private static class SingletonHelper {
        private static final MissFrameState instance = new MissFrameState();
    }
}
