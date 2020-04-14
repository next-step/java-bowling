package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rolls {
    private static final int ROLL_COUNT = 2;
    private List<Pin> rolls;

    public Rolls() {
        rolls = new ArrayList<>();
    }

    public Rolls(List<Pin> rolls) {
        validateRolls();
        this.rolls = Collections.unmodifiableList(new ArrayList<>(rolls));
    }

    public void validateRolls() {
        if (rolls.size() >= ROLL_COUNT) {
            throw new IllegalArgumentException("");
        }
    }

    public void add(Pin pin) {
        validateRolls();
        rolls.add(pin);
    }

    public List<Pin> getRolls() {
        return rolls;
    }
}
