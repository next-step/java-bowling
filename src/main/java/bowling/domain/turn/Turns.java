package bowling.domain.turn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Turns {

    private static final int ZERO = 0;

    private final List<Turn> turns;

    public Turns() {
        this.turns = new ArrayList<>();
    }

    public Turns(final List<Turn> turns) {
        this.turns = new ArrayList<>(turns);
    }

    public Turns(final Turn turns) {
        this.turns = Arrays.asList(turns);
    }

    public Turns bowl(final int pinCount) {
        if (turns.size() == ZERO) {
            return addTurn(Turn.from(pinCount));
        }
        Turn next = turns.get(getLastIndex()).bowl(pinCount);
        return addTurn(next);
    }

    private Turns addTurn(Turn turn) {
        List<Turn> merge = new ArrayList<>(turns);
        merge.add(turn);
        return new Turns(merge);
    }

    public boolean isMoreTwice() {
        return turns.size() > 2;
    }

    public boolean isFinish() {
        if (turns.size() == 0) {
            return false;
        }
        return turns.get(getLastIndex()).isFinish();
    }

    public int size() {
        return turns.size();
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
