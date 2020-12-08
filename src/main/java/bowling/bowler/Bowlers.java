package bowling.bowler;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Bowlers {

    private final List<Bowler> bowlers;

    private Bowlers(List<Bowler> bowlers) {
        this.bowlers = bowlers;
    }

    public static Bowlers from(List<Bowler> bowlers) {
        return new Bowlers(bowlers);
    }

    public List<Bowler> getBowlers() {
        return Collections.unmodifiableList(bowlers);
    }

    public String getBowlerName(int index) {
        return bowlers.get(index).getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bowlers bowlers1 = (Bowlers) o;
        return bowlers.equals(bowlers1.bowlers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bowlers);
    }

    @Override
    public String toString() {
        return "Bowlers{" +
                bowlers +
                '}';
    }

}
