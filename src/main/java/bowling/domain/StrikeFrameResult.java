package bowling.domain;

public class StrikeFrameResult implements FrameResult {
    @Override
    public boolean isStrike() {
        return true;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}
