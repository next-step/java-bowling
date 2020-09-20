package bowling;

public class LastPitchings implements Pitchings {

    private final NormalPitchings normalPitchings;
    private final Pitching bonusPitching;

    private LastPitchings(NormalPitchings normalPitchings, Pitching bonusPitching) {
        this.normalPitchings = normalPitchings;
        this.bonusPitching = bonusPitching;
    }

    public static LastPitchings of() {
        return new LastPitchings(NormalPitchings.ofReady(), Pitching.ofReady());
    }

    @Override
    public void bowl(Pin pin) {
        if (isDone()) {
            throw new IllegalStateException("투구가 모두 완료된 상태 입니다.");
        }

        if (normalPitchings.isFirstDone() && !normalPitchings.isSecondDone()) {
            validateSecondPitching(pin);
            normalPitchings.bowlSecondPitching(pin);
            validateAndSetBonusPitching();
            return;
        }
        if (normalPitchings.isSecondDone()) {
            bonusPitching.bowl(pin);
            return;
        }
        normalPitchings.bowlFirstPitching(pin);
    }

    private void validateSecondPitching(Pin secondPitchingPin) {
        if (normalPitchings.isFirstPitchingClear()) {
            return;
        }

        if (normalPitchings.isOverMaxPins(secondPitchingPin)) {
            throw new IllegalStateException("첫 번째 투구와 두 번째 투구의 합이 범위를 초과했습니다.");
        }
    }

    private void validateAndSetBonusPitching() {
        if (normalPitchings.isSecondPitchingClear()) {
            return;
        }

        if (!normalPitchings.isFirstPitchingClear() && normalPitchings.isSpare()) {
            return;
        }

        bonusPitching.bowl(Pin.ofMin());
    }

    @Override
    public Pin getFirstPin() {
        return normalPitchings.getFirstPin();
    }

    @Override
    public Pin getSecondPin() {
        return normalPitchings.getSecondPin();
    }

    @Override
    public Pin getBonusPin() {
        return bonusPitching.getPin();
    }

    @Override
    public boolean isFirstDone() {
        return normalPitchings.isFirstDone();
    }

    @Override
    public boolean isSecondDone() {
        return normalPitchings.isSecondDone();
    }

    @Override
    public boolean isBonusDone() {
        return bonusPitching.isDone();
    }

    @Override
    public boolean isDone() {
        return isFirstDone() && isSecondDone() && isBonusDone();
    }
}
