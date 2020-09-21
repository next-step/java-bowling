package bowling.domain.core.state;

import bowling.domain.core.FallenPins;
import bowling.domain.core.RolledResult;

import static bowling.domain.core.state.ImmutableTwoFallenPins.strikeTwoFallenPins;

final class Strike implements RolledResult {
    static final RolledResult strike = new Strike();

    @Override
    public int getNextRolledResultMergeScore(RolledResult nextRolledResult) {
        final ImmutableTwoFallenPins twoFallenPins = nextRolledResult.twoFallenPins();

        int score = FallenPins.MAX_FALLEN_PIN_COUNT
            + twoFallenPins.firstFallenPinsValue()
            + twoFallenPins.secondFallenPinsValue();

        if (this == nextRolledResult){
            return score + nextRolledResult.getRolledResultScore();
        }

        return score;
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
