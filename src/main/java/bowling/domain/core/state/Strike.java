package bowling.domain.core.state;

import bowling.domain.core.FallenPins;
import bowling.domain.core.RolledResult;

final class Strike extends AbstractTwoFallenPinsRolledResult {
    static final RolledResult strike = new Strike();

    Strike() {
        super(ImmutableTwoFallenPins.strikeTwoFallenPins());
    }

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
    public String description() {
        return "X";
    }
}
