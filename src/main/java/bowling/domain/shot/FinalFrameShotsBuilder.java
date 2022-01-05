package bowling.domain.shot;

import java.util.List;

import bowling.engine.Shot;

import static bowling.domain.shot.ShotResult.GUTTER;
import static bowling.domain.shot.ShotResult.STRIKE;
import static bowling.engine.Shots.NUMBER_OF_PINS;
import static bowling.engine.Shots.NUMBER_OF_SHOT;

public class FinalFrameShotsBuilder extends FrameShotsBuilder {
    protected FinalFrameShotsBuilder(List<Shot> shots) {
        super(shots);
    }

    public boolean validate() {
        if (hasSpare(shots)
                || hasStrikeOrNoThirdShot(shots)
                && sumWithoutStrike(shots) <= NUMBER_OF_PINS) {
            return true;
        }

        throw new IllegalArgumentException("invalid score: " + shots);
    }

    private boolean hasStrikeOrNoThirdShot(List<Shot> shots) {
        return hasStrike(shots) || shots.size() <= NUMBER_OF_SHOT;
    }

    private boolean hasSpare(List<Shot> shots) {
        return shots.stream()
                .anyMatch(Shot::isSpare);
    }

    private boolean hasStrike(List<Shot> shots) {
        return shots.stream()
                .anyMatch(STRIKE::equals);
    }

    private int sumWithoutStrike(List<Shot> shots) {
        return shots.stream()
                .filter(STRIKE::notEquals)
                .reduce(GUTTER, Shot::add)
                .toInt();
    }
}
