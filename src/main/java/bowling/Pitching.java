package bowling;

import java.util.HashMap;
import java.util.Map;

public enum Pitching {
    GUTTER(0),
    ONE_PIN(1),
    TWO_PINS(2),
    THREE_PINS(3),
    FOUR_PINS(4),
    FIVE_PINS(5),
    SIX_PINS(6),
    SEVEN_PINS(7),
    EIGHT_PINS(8),
    NINE_PINS(9),
    STRIKE(10),
    SPARE(null);

    public static final String INVALID_KNOCK_DOWN_PINS_ARGUMENT = "유효하지 않은 knockDownPins 입력값입니다.";
    private final Integer score;

    Pitching(Integer score) {
        this.score = score;
    }

    public static final Map<Integer, Pitching> pitchingByScore = new HashMap<>();
    static {
        for (Pitching pitching : Pitching.values()) {
            pitchingByScore.put(pitching.score, pitching);
        }
    }

    public static Pitching getPitching(int knockDownPins) {
        validateKnockDownPinsInput(knockDownPins);
        return pitchingByScore.get(knockDownPins);
    }

    private static void validateKnockDownPinsInput(int knockDownPins) {
        if (knockDownPins < GUTTER.score || knockDownPins > STRIKE.score) {
            throw new IllegalArgumentException(INVALID_KNOCK_DOWN_PINS_ARGUMENT);
        }
    }

    public static Pitching getPitching(int knockDownPins, Pitching previousPitching) {
        if (knockDownPins + previousPitching.score == STRIKE.score) {
            return SPARE;
        }

        return getPitching(knockDownPins);
    }
}
