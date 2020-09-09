package bowling.domain.core;

abstract class AbstractTwoFallenPinsRolledResult implements RolledResult {
    private final ImmutableTwoFallenPins towFallenPins;

    public AbstractTwoFallenPinsRolledResult(ImmutableTwoFallenPins towFallenPins) {
        this.towFallenPins = towFallenPins;
    }

    @Override
    public int numberOfPinsFallingByAttemptCount(int rollingAttemptCount) {
        return towFallenPins.getFallenPins(rollingAttemptCount);
    }

    String gutterOrFallenPinValue(int rollingAttemptCount) {
        if (towFallenPins.isGutter(rollingAttemptCount)){
            return "-";
        }
        return String.valueOf(towFallenPins.getFallenPins(rollingAttemptCount));
    }
}
