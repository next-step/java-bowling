package bowling.domain;

import java.util.Objects;

public class Pin {

    private int countOfHit;

    public Pin(int countOfHit) {
        this.countOfHit = countOfHit;
        validate();
    }

    private void validate() {
        if (countOfHit < 0) {
            throw new IllegalArgumentException("적중한 볼링핀의 갯수는 0 미만이 될 수 없습니다.");
        }

        if (countOfHit > 10) {
            throw new IllegalArgumentException("적중한 볼링핀의 갯수는 10 초과가 될 수 없습니다.");
        }
    }

    public boolean isAvailable(int count) {
        if (countOfHit + count > 10) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return countOfHit == pin.countOfHit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfHit);
    }
}
