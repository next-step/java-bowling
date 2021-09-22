package bowling.domain.frame;

import java.util.Objects;

public class Inning {
    private static final int FIRST_INNING = 1;

    private final int inning;

    private Inning(int inning) {
        this.inning = inning;
    }

    public static Inning from(int inning) {
        return new Inning(inning);
    }

    public static Inning first() {
        return new Inning(FIRST_INNING);
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

}
