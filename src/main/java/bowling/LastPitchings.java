package bowling;

public class LastPitchings implements Pitchings {

    private final NormalPitchings normalPitchings;
    private final Pitching bonusPitching;

    private LastPitchings(NormalPitchings normalPitchings, Pitching bonusPitching) {
        this.normalPitchings = normalPitchings;
        this.bonusPitching = bonusPitching;
    }

    public static LastPitchings of(NormalPitchings normalPitchings, Pitching pitching) {
        return new LastPitchings(normalPitchings, pitching);
    }

    public static LastPitchings ofReady() {
        return new LastPitchings(NormalPitchings.ofReady(), Pitching.ofReady());
    }

    @Override
    public void bowl(Pin pin) {
        if (isDone()) {
            throw new IllegalStateException("투구가 모두 완료된 상태 입니다.");
        }

        if (!normalPitchings.isFirstDone()) {
            normalPitchings.bowlFirstPitching(pin);
            checkAndSetSecondPitching();
            return;
        }

        if (!normalPitchings.isSecondDone()) {
            validateSecondPitching(pin);
            normalPitchings.bowlSecondPitching(pin);
            validateAndSetBonusPitching();
            return;
        }

        bonusPitching.bowl(pin);
    }

    private void checkAndSetSecondPitching() {
        if (normalPitchings.isStrike()) {
            normalPitchings.bowlSecondPitching(Pin.ofMin());
        }
    }

    private void validateSecondPitching(Pin secondPitchingPin) {
        if (normalPitchings.isOverMaxPins(secondPitchingPin)) {
            throw new IllegalStateException("첫 번째 투구와 두 번째 투구의 합이 범위를 초과했습니다.");
        }
    }

    private void validateAndSetBonusPitching() {
        if (normalPitchings.isStrike() || normalPitchings.isSpare()) {
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
        return normalPitchings.isDone() && isBonusDone();
    }

    @Override
    public boolean isStrike() {
        return normalPitchings.isStrike();
    }

    @Override
    public boolean isSpare() {
        return normalPitchings.isSpare();
    }

    @Override
    public int getFirstScore() {
        return normalPitchings.getFirstScore();
    }

    @Override
    public int calculateScore() {
        return normalPitchings.calculateScore() + bonusPitching.getScore();
    }
}
