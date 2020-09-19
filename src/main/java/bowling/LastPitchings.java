package bowling;

public class LastPitchings implements Pitchings {

    private final Pitching firstPitching;
    private final Pitching secondPitching;
    private final Pitching bonusPitching;

    private LastPitchings(Pitching firstPitching, Pitching secondPitching, Pitching bonusPitching) {
        this.firstPitching = firstPitching;
        this.secondPitching = secondPitching;
        this.bonusPitching = bonusPitching;
    }

    @Override
    public void bowl(Pin pin) {
        if (isDone()) {
            throw new IllegalStateException("투구가 모두 완료된 상태 입니다.");
        }

        if (firstPitching.isDone() && !secondPitching.isDone()) {
            validateSecondPitching(pin);
            secondPitching.bowl(pin);
            validateAndSetBonusPitching();
            return;
        }
        if (secondPitching.isDone()) {
            bonusPitching.bowl(pin);
            return;
        }
        firstPitching.bowl(pin);
    }

    private void validateSecondPitching(Pin secondPitchingPin) {
        if (firstPitching.isClear()) return;

        Pin firstPitchingPin = firstPitching.getPin();
        if (firstPitchingPin.getPin() + secondPitchingPin.getPin() > Pin.MAX_PINS) {
            throw new IllegalStateException("첫 번째 투구와 두 번째 투구의 합이 범위를 초과했습니다.");
        }
    }

    private void validateAndSetBonusPitching() {
        if (secondPitching.isClear()) {
            return;
        };

        Pin firstPitchingPin = firstPitching.getPin();
        Pin secondPitchingPin = secondPitching.getPin();
        if (!firstPitching.isClear()
                && firstPitchingPin.getPin() + secondPitchingPin.getPin() == Pin.MAX_PINS) return;

        bonusPitching.bowl(Pin.ofMin());
    }

    @Override
    public Pin getFirstPin() {
        return firstPitching.getPin();
    }

    @Override
    public Pin getSecondPin() {
        return secondPitching.getPin();
    }

    @Override
    public Pin getBonusPin() {
        return bonusPitching.getPin();
    }

    @Override
    public boolean isFirstDone() {
        return firstPitching.isDone();
    }

    @Override
    public boolean isSecondDone() {
        return secondPitching.isDone();
    }

    @Override
    public boolean isBonusDone() {
        return bonusPitching.isDone();
    }

    @Override
    public boolean isDone() {
        return isFirstDone() && isSecondDone() && isBonusDone();
    }

    public static LastPitchings of() {
        return new LastPitchings(Pitching.ofReady(), Pitching.ofReady(), Pitching.ofReady());
    }
}
