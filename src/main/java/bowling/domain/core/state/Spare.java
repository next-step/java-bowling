package bowling.domain.core.state;

import bowling.domain.core.FallenPins;
import bowling.domain.core.RolledResult;
import bowling.domain.frame.TerminateFrame;

public final class Spare implements RolledResult {
    private ImmutableTwoFallenPins immutableTwoFallenPins;

    Spare(ImmutableTwoFallenPins twoFallenPins) {
        this.immutableTwoFallenPins = twoFallenPins;
    }

    public static RolledResult expectSpareAfterBonusBowl(RolledResult rolledResult){
        if (rolledResult instanceof Spare){
            return new SpareAfterBonusBowl(rolledResult);
        }
        return rolledResult;
    }

    private static boolean isFinalTryCount(int rollingAttemptCount) {
        return (TerminateFrame.MAX_TRY_COUNT_SIZE - 1) == rollingAttemptCount;
    }

    @Override
    public int getNextRolledResultMergeScore(RolledResult nextRolledResult) {
        return FallenPins.MAX_FALLEN_PIN_COUNT
            + nextRolledResult.twoFallenPins()
                              .firstFallenPinsValue();
    }

    @Override
    public ImmutableTwoFallenPins twoFallenPins() {
        return immutableTwoFallenPins;
    }

    @Override
    public String description() {
        return String.format("%s|/", gutterOrFallenPinValue(0));
    }

    @Override
    public String toString() {
        return "Spare{" + immutableTwoFallenPins.getFallenPins(0) + ", " + immutableTwoFallenPins.getFallenPins(1) +
            '}';
    }
}
