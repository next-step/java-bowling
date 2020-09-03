package bowling.domain.core;

import bowling.domain.TerminateFrame;

public final class Spare extends AbstractSecondRolledResult {
    Spare(Pins first, Pins second) {
        super(first, second);
    }

    public static RolledResult expectSpareAfterBonusBowl(int tryCount, RolledResult rolledResult){
        if ((TerminateFrame.MAX_TRY_COUNT_SIZE - 1) == tryCount && rolledResult.isNotCompleteState()){
            return new SpareAfterBonusBowl(rolledResult);
        }
        return rolledResult;
    }

    @Override
    public String description() {
        return String.format("%s|/", gutterOrFallenPinCount(0));
    }
}
