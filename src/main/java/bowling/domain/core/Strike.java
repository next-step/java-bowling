package bowling.domain.core;

final class Strike implements RolledResult {
    static final RolledResult strike = new Strike();

    @Override
    public String description() {
        return "X";
    }

    @Override
    public int numberOfPinsFallingByAttemptCount(int rollingTryCount) {
        if (0 == rollingTryCount) {
            return FallenPins.MAX_FALLEN_PIN_COUNT;
        }
        return FallenPins.MIN_FALLEN_PIN_COUNT;
    }
}
