package bowling.domain.core;

final class SpareAfterBonusBowl extends AbstractTwoFallenPinsRolledResult {
    SpareAfterBonusBowl(RolledResult rolledResult) {
        super(new ImmutableTwoFallenPins(firstFallenPins(rolledResult), FallenPins.zero()));
    }

    private static FallenPins firstFallenPins(RolledResult rolledResult) {
        return FallenPins.of(rolledResult.numberOfPinsFallingByAttemptCount(0));
    }

    @Override
    public String description() {
        return String.format("%s",gutterOrFallenPinValue(0));
    }
}
