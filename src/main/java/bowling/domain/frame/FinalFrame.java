package bowling.domain.frame;

import bowling.domain.status.FrameStatus;
import bowling.domain.status.Ready;
import bowling.domain.status.FinalFirstBowl;
import bowling.domain.status.FinalSecondBowl;
import bowling.domain.status.FinalThirdBowl;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {

    public static final int FRAME_MAX_SCORE = 10;

    private List<Integer> scores = new ArrayList<>();
    private boolean isEnd;
    private FrameStatus status;

    public FinalFrame() {
        this.status = new Ready(true);
    }

    public void bowl(int score) {
        this.scores.add(score);
        this.status = status.bowl(score);
        this.isEnd = isEndCondition(score);
    }

    public boolean hasSize(int size) {
        return scores.size() == size;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public boolean isEnd() {
        return isEnd;
    }

    @Override
    public boolean isEndCondition(int score) {
        return status instanceof FinalThirdBowl || (status instanceof FinalSecondBowl && status.isEnd());
    }

    public boolean hasNotFinalFrame() {
        return this.hasSize(0);
    }

    public FrameStatus getStatus() {
        return status;
    }
}
