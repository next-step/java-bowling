package bowling.domain.turn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Turns {

    private final List<Turn> turns;

    public Turns(final List<Turn> turns) {
        this.turns = new ArrayList<>(turns);
    }

    public Turns(final Turn turns) {
        this.turns = Arrays.asList(turns);
    }
}
