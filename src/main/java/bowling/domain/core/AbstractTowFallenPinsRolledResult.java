package bowling.domain.core;

abstract class AbstractTowFallenPinsRolledResult implements RolledResult {
    private final ImmutableTowFallenPins towFallenPins;

    public AbstractTowFallenPinsRolledResult(ImmutableTowFallenPins towFallenPins) {
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
