package bowling.domain;

import java.util.Arrays;
import java.util.List;

public class Fixture {
    public static final List<Integer> NINE_STRIKE_STATE = Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10);
    public static final List<Integer> END_GAME_STATE_EXAMPLE1 = Arrays.asList(1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2,
        1, 2, 1, 2, 1, 2);
    public static final List<Integer> END_GAME_STATE_EXAMPLE2 = Arrays.asList(1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2,
        1, 2, 1, 2, 5, 5, 10);
}
