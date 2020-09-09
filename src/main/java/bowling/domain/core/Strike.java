package bowling.domain.core;

final class Strike implements RolledResult {
    @Override
    public String description() {
        return "X";
    }

    @Override
    public int numberOfPinsFallingByAttemptCount(int rollingTryCount) {
        if (0 == rollingTryCount) {
            return Pins.MAX_FALLEN_PIN_COUNT;
        }
        return Pins.MIN_FALLEN_PIN_COUNT;
    }
}
