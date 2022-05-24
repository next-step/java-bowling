package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public enum Strike {
    STRIKE(1),
    DOUBLE(2),
    TURKEY(3),
    FOUR_BEGGER(4),
    FIVE_BEGGER(5),
    SIX_BEGGER(6),
    SEVEN_IN_A_ROW(7),
    EIGHT_STRIKE(8),
    NINE_STRIKE(9),
    TEN_STRIKE(10),
    ELEVEN_STRIKE(11),
    PERFERT_GAME(12);

    private final int count;

    Strike(int count) {
        this.count = count;
    }

    private static Map<Integer, Strike> map = new HashMap<>();
    static {
        map.put(1, STRIKE);
        map.put(2, DOUBLE);
        map.put(3, TURKEY);
        map.put(4, FOUR_BEGGER);
        map.put(5, FIVE_BEGGER);
        map.put(6, SIX_BEGGER);
        map.put(7, SEVEN_IN_A_ROW);
        map.put(8, EIGHT_STRIKE);
        map.put(9, NINE_STRIKE);
        map.put(10, TEN_STRIKE);
        map.put(11, ELEVEN_STRIKE);
        map.put(12, PERFERT_GAME);
    }

    public static Strike valueOf(int count) {
        return map.get(count);
    }

}
