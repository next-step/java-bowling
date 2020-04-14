package bowling.domain.turn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Turns {

    private final List<Turn> turns;

    public Turns(final List<Turn> turns) {
        this.turns = new ArrayList<>(turns);
    }

    public Turns(final Turn turns) {
        this.turns = Arrays.asList(turns);
    }

    public Turns bowl(final int pinCount) {
        Turn next = turns.get(getLastIndex()).bowl(pinCount);
        return addTurn(next);
    }

    private Turns addTurn(Turn turn) {
        List<Turn> merge = new ArrayList<>(turns);
        merge.add(turn);
        return new Turns(merge);
    }

    private int getLastIndex() {
        return turns.size() - 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turns turns1 = (Turns) o;
        return Objects.equals(turns, turns1.turns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(turns);
    }
}
