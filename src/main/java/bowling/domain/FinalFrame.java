package bowling.domain;

import java.util.List;

import bowling.engine.collection.FirstClassList;
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
            throw new IllegalArgumentException("invalid score: " + sum);
        }

        return new FinalFrame(shots);
    }

    @Override
    public boolean isSpareChallenge() {
        return size() != 0 && last() != STRIKE && !last().isSpare();
    }

    @Override
    public boolean completed() {
        return size() == NUMBER_OF_FINAL_SHOT | (!hasThirdChance() && size() == NUMBER_OF_SHOT);
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
