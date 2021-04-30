package bowling.domain;


import java.util.Objects;

public class Pinfall {
    private static final int MAX_PINFALL = 10;

    private final int pinfall;

    public Pinfall(int pinfall) {
        if (pinfall > MAX_PINFALL) {
            throw new IllegalArgumentException("넘어진 핀의 개수가 잘못되었습니다");
        }
        this.pinfall = pinfall;
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

    public Pinfall add(Pinfall pinfall) {
        return new Pinfall(this.pinfall + pinfall.pinfall);
    }

    public boolean isStrike() {
        return pinfall == MAX_PINFALL;
    }
}
