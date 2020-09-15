package bowling.domain.core;

import static bowling.domain.core.RolledResultFactory.notAtRolledResult;

final class Strike extends AbstractTwoFallenPinsRolledResult {
    static final RolledResult strike = new Strike();

    public Strike() {
        super(ImmutableTwoFallenPins.strike());
    }

    @Override
    public int getNextRolledResultMergeScore(RolledResult nextRolledResult) {
        final ImmutableTwoFallenPins twoFallenPins = nextRolledResult.twoFallenPins();

        int score = FallenPins.MAX_FALLEN_PIN_COUNT
            + twoFallenPins.firstFallenPinsValue()
            + twoFallenPins.secondFallenPinsValue();

        if (this == nextRolledResult){
            return score + nextRolledResult.getNextRolledResultMergeScore(notAtRolledResult());
        }

        return score;
    }

    @Override
    public String description() {
        return "X";
    }
}
