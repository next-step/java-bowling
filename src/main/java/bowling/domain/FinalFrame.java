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
        if (hasNoSpare(shots) && sumWithoutStrike(shots) > NUMBER_OF_PINS) {
            throw new IllegalArgumentException("invalid score: " + shots);
        }

        return new FinalFrame(shots);
    }

    private static boolean hasNoSpare(List<Shot> shots) {
        return shots.stream()
                .noneMatch(Shot::isSpare);
    }

    private static int sumWithoutStrike(List<Shot> shots) {
        return sum(shots.stream()
                .filter(STRIKE::notEquals));
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
