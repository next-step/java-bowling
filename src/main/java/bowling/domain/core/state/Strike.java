package bowling.domain.core.state;

import bowling.domain.core.FallenPins;
import bowling.domain.core.RolledResult;

import static bowling.domain.core.state.ImmutableTwoFallenPins.strikeTwoFallenPins;
import static bowling.domain.core.state.NotAtRolledResult.notAtRolledResult;

final class Strike implements RolledResult {
    static final RolledResult strike = new Strike();

    @Override
    public Score getScore() {
        return new Score(FallenPins.MAX_FALLEN_PIN_COUNT, 2);
    }

    @Override
    public Score calculateScore(Score score) {
        if (score.hasNotAttemptLeft()) {
            return score;
        }
        return score.sum(FallenPins.MAX_FALLEN_PIN_COUNT);
    }

    @Override
    public RolledResult nextRolledResult(int fallenPinsValue) {
        return notAtRolledResult().nextRolledResult(fallenPinsValue);
    }

    @Override
    public ImmutableTwoFallenPins twoFallenPins() {
        return strikeTwoFallenPins();
    }

    @Override
    public String description() {
        return "X";
    }
}
