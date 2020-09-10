package bowling.domain.core;

import bowling.domain.TerminateFrame;

final class Gutter implements RolledResult {
    static final RolledResult gutter = new Gutter();

    @Override
    public int tryCountByTerminateFrame() {
        return TerminateFrame.MAX_TRY_COUNT_SIZE;
    }

    @Override
    public String description() {
        return "-";
    }

    @Override
    public int numberOfPinsFallingByAttemptCount(int rollingTryCount) {
        return FallenPins.MIN_FALLEN_PIN_COUNT;
    }
}
