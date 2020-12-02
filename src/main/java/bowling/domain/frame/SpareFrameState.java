package bowling.domain.frame;

import bowling.domain.FrameEnum;
import bowling.domain.Rolls;

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
    public int getScore(FrameContext context, Rolls rolls) {
        return rolls.sum(
                context.getRollIndex(),
                offset
        );
    }

    @Override
    public boolean hasScore(FrameContext context, Rolls rolls) {
        return context.getRollIndex() + offset <= rolls.size();
    }

    @Override
    public void update(FrameContext context, Rolls rolls) {}

    private static class SingletonHelper {
        private static final SpareFrameState instance = new SpareFrameState();
    }
}
