package bowling.domain.core;

import bowling.domain.frame.TerminateFrame;

public final class Spare extends AbstractTwoFallenPinsRolledResult {

    public Spare(ImmutableTwoFallenPins towFallenPins) {
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
    public int getNextRolledResultMergeScore(RolledResult nextRolledResult) {
        return super.getNextRolledResultMergeScore(nextRolledResult)
            + nextRolledResult.twoFallenPins()
                              .firstFallenPinsValue();
    }

    @Override
    public String description() {
        return String.format("%s|/", gutterOrFallenPinValue(0));
    }
}
