package bowling.domain.core.state;

import bowling.domain.core.RolledResult;

public abstract class AbstractTwoFallenPinsRolledResult implements RolledResult {
    protected final ImmutableTwoFallenPins twoFallenPins;

    protected AbstractTwoFallenPinsRolledResult(ImmutableTwoFallenPins twoFallenPins) {
        this.twoFallenPins = twoFallenPins;
    }

    protected String gutterOrFallenPinValue(int rollingAttemptCount) {
        if (twoFallenPins.isGutter(rollingAttemptCount)){
            return "-";
        }
        return String.valueOf(twoFallenPins.getFallenPins(rollingAttemptCount));
    }

    @Override
    public ImmutableTwoFallenPins twoFallenPins() {
        return twoFallenPins;
    }

    @Override
    public int getNextRolledResultMergeScore(RolledResult nextRolledResult) {
        return twoFallenPins.totalScore();
    }
}
