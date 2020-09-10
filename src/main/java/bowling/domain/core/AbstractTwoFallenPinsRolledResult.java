package bowling.domain.core;

abstract class AbstractTwoFallenPinsRolledResult implements RolledResult {
    private final ImmutableTwoFallenPins twoFallenPins;

    public AbstractTwoFallenPinsRolledResult(ImmutableTwoFallenPins twoFallenPins) {
        this.twoFallenPins = twoFallenPins;
    }

    @Override
    public int numberOfPinsFallingByAttemptCount(int rollingAttemptCount) {
        return twoFallenPins.getFallenPins(rollingAttemptCount);
    }

    String gutterOrFallenPinValue(int rollingAttemptCount) {
        if (twoFallenPins.isGutter(rollingAttemptCount)){
            return "-";
        }
        return String.valueOf(twoFallenPins.getFallenPins(rollingAttemptCount));
    }
}
