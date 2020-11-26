package bowling.domain;

import java.util.ArrayList;
import java.util.List;

class Rolls {
    private final List<Roll> rolls = new ArrayList<>();

    void add(Roll roll) {
        rolls.add(roll);
    }

    List<Integer> toIntegers() {
        return Roll.toIntegers(rolls);
    }
}
