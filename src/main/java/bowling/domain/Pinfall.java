package bowling.domain;


import bowling.IllegalPinFallException;

import java.util.Objects;

public class Pinfall {
    private static final int MAX_PINFALL = 10;

    private final int pinfall;

    public Pinfall(int pinfall) {
        if (pinfall > MAX_PINFALL) {
            throw new IllegalPinFallException("넘어진 핀의 개수가 잘못되었습니다");
        }
        this.pinfall = pinfall;
    }

    public Pinfall add(Pinfall pinfall) {
        return new Pinfall(this.pinfall + pinfall.pinfall);
    }

    public boolean isStrike() {
        return pinfall == MAX_PINFALL;
    }

    public int number() {
        return pinfall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pinfall pinfall1 = (Pinfall) o;
        return pinfall == pinfall1.pinfall;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinfall);
    }
}
