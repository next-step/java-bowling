package bowling.domain.frame;

public class FinalFrame extends NormalFrame {
    protected Frame nextFrame;

    @Override
    public Frame createFrame(boolean isFinalFrame) {
        nextFrame = new BonusFrame(stateHistory.getLatestState().countToCalculate());

        return nextFrame;
    }
}
