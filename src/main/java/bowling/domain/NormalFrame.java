package bowling.domain;

import java.util.Objects;

public class NormalFrame {

    private int index;
    private Pin pin;

    public NormalFrame(int index, int countOfHit) {
        this.index = index;
        this.pin = new Pin(countOfHit);
    }

    public int getScore() {
        return pin.getCountOfHit();
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return index == that.index &&
                Objects.equals(pin, that.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, pin);
    }
}
