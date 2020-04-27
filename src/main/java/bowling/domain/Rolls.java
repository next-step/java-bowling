package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Rolls {
    private static final int MINIMUM_ROLL_NUMBER = 0;
    private static final int MAXIMUM_ROLL_NUMBER = 20;

    public static List<Integer> generateRolls() {
        List<Integer> rolls = new ArrayList<>();

        for (int i = MINIMUM_ROLL_NUMBER; i < MAXIMUM_ROLL_NUMBER; i++) {
            rolls.add(i);
        }
        return rolls;
    }
}
