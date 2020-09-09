package bowling.domain.core;

final class SpareAfterBonusBowl extends AbstractTowFallenPinsRolledResult {
    SpareAfterBonusBowl(RolledResult rolledResult) {
        super(new ImmutableTowFallenPins(firstFallenPins(rolledResult), Pins.zero()));
    }

    private static Pins firstFallenPins(RolledResult rolledResult) {
        return Pins.of(rolledResult.numberOfPinsFallingByAttemptCount(0));
    }

    @Override
    public String description() {
        return String.format("%s",gutterOrFallenPinValue(0));
    }
}
