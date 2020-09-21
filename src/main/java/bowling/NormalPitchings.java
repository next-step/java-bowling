package bowling;

import java.util.Objects;

public class NormalPitchings implements Pitchings {

    private final Pitching firstPitching;
    private final Pitching secondPitching;

    private NormalPitchings(Pitching firstPitching, Pitching secondPitching) {
        this.firstPitching = firstPitching;
        this.secondPitching = secondPitching;
    }

    public static NormalPitchings of(Pitching firstPitching, Pitching secondPitching) {
        return new NormalPitchings(firstPitching, secondPitching);
    }

    public static NormalPitchings ofReady() {
        return new NormalPitchings(Pitching.ofReady(), Pitching.ofReady());
    }

    @Override
    public void bowl(Pin pin) {
        if (isDone()) {
            throw new IllegalStateException("투구가 모두 완료된 상태 입니다.");
        }

        if (firstPitching.isDone()) {
            validateSecondPitching(pin);
            secondPitching.bowl(pin);
            return;
        }
        firstPitching.bowl(pin);

        if (firstPitching.isClear()) {
            secondPitching.bowl(Pin.ofMin());
        }
    }

    private void validateSecondPitching(Pin secondPitchingPin) {
        if (firstPitching.isOverMaxPins(secondPitchingPin)) {
            throw new IllegalStateException("첫 번째 투구와 두 번째 투구의 합이 범위를 초과했습니다.");
        }
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
    public boolean isFirstDone() {
        return firstPitching.isDone();
    }

    @Override
    public boolean isSecondDone() {
        return secondPitching.isDone();
    }

    @Override
    public boolean isDone() {
        return isFirstDone() && isSecondDone();
    }

    public void bowlFirstPitching(Pin pin) {
        firstPitching.bowl(pin);
    }

    public void bowlSecondPitching(Pin pin) {
        secondPitching.bowl(pin);
    }

    public boolean isStrike() {
        return firstPitching.isClear();
    }

    public boolean isOverMaxPins(Pin pin) {
        return firstPitching.isOverMaxPins(pin);
    }

    public boolean isSpare() {
        return firstPitching.isSpare(secondPitching);
    }

    @Override
    public int getFirstScore() {
        return firstPitching.getScore();
    }

    @Override
    public int calculateScore() {
        return firstPitching.getScore() + secondPitching.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NormalPitchings that = (NormalPitchings) o;

        if (!Objects.equals(firstPitching, that.firstPitching))
            return false;
        return Objects.equals(secondPitching, that.secondPitching);
    }

    @Override
    public int hashCode() {
        int result = firstPitching != null ? firstPitching.hashCode() : 0;
        result = 31 * result + (secondPitching != null ? secondPitching.hashCode() : 0);
        return result;
    }
}
