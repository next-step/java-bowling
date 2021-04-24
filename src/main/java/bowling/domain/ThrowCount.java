package bowling.domain;

import java.util.Objects;

public class ThrowCount {

    public static int MIN_THROW_COUNT = 0;
    public static int MAX_THROW_COUNT = 3;
    
    public final int throwCount;

    public ThrowCount(int throwCount) {
        this.throwCount = throwCount;
    }

    public static ThrowCount of(int throwCount) {
        if (MIN_THROW_COUNT < 0 || MAX_THROW_COUNT > 3) {
            throw new IllegalArgumentException("공던지는 기회는 0 ~ 3번 사이입니다.");
        }
        return new ThrowCount(throwCount);
    }

    public static ThrowCount fisrt() {
        return new ThrowCount(MIN_THROW_COUNT);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThrowCount that = (ThrowCount) o;
        return throwCount == that.throwCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(throwCount);
    }
}
