package bowling.domain.core.state;

import bowling.domain.core.RolledResult;

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

    @Override
    public int tryCountByTerminateFrame() {
        return 2;
    }

    @Override
    public int getNextRolledResultMergeScore(RolledResult nextRolledResult) {
        if (isCompleteState() && nextRolledResult.isCompleteState()) {
            return nextRolledResult.twoFallenPins()
                                  .firstFallenPinsValue();
        }
        return 0;
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
