package bowling.domain;

import java.util.List;

import bowling.engine.collection.FirstClassList;
import bowling.engine.Frame;
import bowling.engine.Shot;

import static bowling.domain.ShotResult.STRIKE;

public class FinalFrame extends NormalFrame {
    protected FinalFrame(List<Shot> shots) {
        super(FrameSequence.FINAL_FRAME, shots);
    }

    static FinalFrame of(List<Shot> shots) {
        int sum = sum(shots.stream());
        long strikeCount = shots.stream()
                .filter(STRIKE::equals).count();
        if ((sum > FrameScore.DOUBLE_SCORE && strikeCount < ShotResult.DOUBLE_STRIKE_COUNT)
                || sum > FrameScore.TURKEY_SCORE) {
            throw new IllegalStateException("invalid score: " + sum);
        }

        return new FinalFrame(shots);
    }

    @Override
    public Frame nextShot(Shot shot) {
        if (completed()) {
            throw new IllegalStateException("the frame is already completed.");
        }

        return of(append(shot).collect());
    }

    @Override
    public boolean completed() {
        return hasThirdChance() ? size() == NUMBER_OF_FINAL_SHOT : super.completed();
    }

    @Override
    public boolean hasThirdChance() {
        return isClear() || elementOf(FirstClassList.HEAD) == STRIKE;
    }

    @Override
    public boolean isFinal() {
        return true;
    }
}
