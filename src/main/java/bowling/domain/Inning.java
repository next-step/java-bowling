package bowling.domain;

import java.util.Objects;

public class Inning {
    private static final int firstInning = 1;
    private final int inning;

    public Inning(int inning) {
        this.inning = inning;
    }

    public static Inning first() {
        return new Inning(firstInning);
    }

    public int asInt() {
        return inning;
    }

    public Inning nextInning() {
        return new Inning(inning + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inning inning1 = (Inning) o;
        return inning == inning1.inning;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inning);
    }

    @Override
    public String toString() {
        return "Inning{" +
                "inning=" + inning +
                '}';
    }
}
