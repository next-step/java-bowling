package bowling.domain.frame;

import bowling.domain.status.FinalSecondBowl;
import bowling.domain.status.FinalThirdBowl;
import bowling.domain.status.FrameStatus;
import bowling.domain.status.Ready;

public class FinalFrame implements Frame {

    private boolean isEnd;
    private FrameStatus status;

    public FinalFrame() {
        this.status = new Ready(true);
    }

    public void bowl(int score) {
        this.status = status.bowl(score);
        this.isEnd = isEndCondition(score);
    }

    public boolean isEnd() {
        return isEnd;
    }

    @Override
    public boolean isEndCondition(int score) {
        return status instanceof FinalThirdBowl || (status instanceof FinalSecondBowl && status.isEnd());
    }

    public FrameStatus getStatus() {
        return status;
    }
}
