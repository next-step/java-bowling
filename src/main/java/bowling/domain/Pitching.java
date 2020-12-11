package bowling.domain;

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

    public static final String INVALID_REMAIN_PINS_SIZE_INPUT_ERR_MSG = "남은 볼링 핀의 갯수보다 많은 핀을 쓰러트릴 수 없습니다.";
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

    public static Pitching getPitching(KnockDownPins knockDownPins) {
        return pitchingByScore.get(knockDownPins.getValue());
    }

    public static Pitching getPitching(KnockDownPins knockDownPins, Pitching previousPitching) {
        if (previousPitching == SPARE) {
            return getPitching(knockDownPins);
        }

        if (knockDownPins.getValue() + previousPitching.score == STRIKE.score) {
            return SPARE;
        }

        if (previousPitching != STRIKE && knockDownPins.getValue() + previousPitching.score > STRIKE.score) {
            throw new IllegalArgumentException(INVALID_REMAIN_PINS_SIZE_INPUT_ERR_MSG);
        }

        return getPitching(knockDownPins);
    }
}
