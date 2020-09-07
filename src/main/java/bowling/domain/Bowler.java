package bowling.domain;

import java.util.Objects;

public class Bowler {

    private String name;

    private Bowler(String name) {
        this.name = name;
    }

    public static Bowler from(String name) {
        return new Bowler(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bowler bowler = (Bowler) o;
        return Objects.equals(name, bowler.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
