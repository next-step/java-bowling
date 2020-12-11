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

    static final Map<Integer, Pitching> pitchingByScore = new HashMap<>();
    static {
        for (Pitching pitching : Pitching.values()) {
            Integer score = pitching.score;
            pitchingByScore.put(score, pitching);
        }
    }

    public static Pitching getPitching(KnockDownPins knockDownPins) {
        int knockDownPinsValue = knockDownPins.getValue();
        return pitchingByScore.get(knockDownPinsValue);
    }

    public static Pitching getPitching(KnockDownPins knockDownPins, Pitching previousPitching) {
        if (previousPitching == SPARE) {
            return getPitching(knockDownPins);
        }

        if (isSpare(knockDownPins, previousPitching)) {
            return SPARE;
        }

        validateRemainPinSize(knockDownPins, previousPitching);

        return getPitching(knockDownPins);
    }

    private static boolean isSpare(KnockDownPins knockDownPins, Pitching previousPitching) {
        return knockDownPins.getValue() + previousPitching.score == STRIKE.score;
    }

    private static void validateRemainPinSize(KnockDownPins knockDownPins, Pitching previousPitching) {
        int knockDownPinsValue = knockDownPins.getValue();
        int previousPitchingScore = previousPitching.score;
        int strikeScore = STRIKE.score;
        if (previousPitching != STRIKE && knockDownPinsValue + previousPitchingScore > strikeScore) {
            throw new IllegalArgumentException(INVALID_REMAIN_PINS_SIZE_INPUT_ERR_MSG);
        }
    }
}
