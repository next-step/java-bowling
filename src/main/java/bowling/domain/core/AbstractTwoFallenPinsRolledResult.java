package bowling.domain.core;

abstract class AbstractTwoFallenPinsRolledResult implements RolledResult {
    protected final ImmutableTwoFallenPins twoFallenPins;

    public AbstractTwoFallenPinsRolledResult(ImmutableTwoFallenPins twoFallenPins) {
        this.twoFallenPins = twoFallenPins;
    }

    @Override
    public ImmutableTwoFallenPins twoFallenPins() {
        return twoFallenPins;
    }

    String gutterOrFallenPinValue(int rollingAttemptCount) {
        if (twoFallenPins.isGutter(rollingAttemptCount)){
            return "-";
        }
        return String.valueOf(twoFallenPins.getFallenPins(rollingAttemptCount));
    }

    @Override
    public int getNextRolledResultMergeScore(RolledResult nextRolledResult) {
        return twoFallenPins.totalScore();
    }
}
