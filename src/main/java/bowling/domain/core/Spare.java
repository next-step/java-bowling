package bowling.domain.core;

import bowling.domain.TerminateFrame;

public final class Spare extends AbstractTowFallenPinsRolledResult {

    public Spare(ImmutableTowFallenPins towFallenPins) {
        super(towFallenPins);
    }

    public static RolledResult expectSpareAfterBonusBowl(int rollingAttemptCount, RolledResult rolledResult){
        if (isFinalTryCount(rollingAttemptCount) && rolledResult.isNotCompleteState()){
            return new SpareAfterBonusBowl(rolledResult);
        }
        return rolledResult;
    }

    private static boolean isFinalTryCount(int rollingAttemptCount) {
        return (TerminateFrame.MAX_TRY_COUNT_SIZE - 1) == rollingAttemptCount;
    }

    @Override
    public String description() {
        return String.format("%s|/", gutterOrFallenPinValue(0));
    }
}
