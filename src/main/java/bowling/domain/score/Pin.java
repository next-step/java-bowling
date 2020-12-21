package bowling.domain.score;

import java.util.Objects;

/**
 * Created By mand2 on 2020-12-18.
 */
public class Pin {

    public static final int MIN_PINS = 0;
    public static final int MAX_PINS = 10;
    public static final String MESSAGE_SCORE_RANGE = "투구 점수는 0~10 사이입니다";
    
    private final int knockDownPins;

    private Pin(int knockDownPins) {
        this.knockDownPins = knockDownPins;
    }

    public static Pin from(int score) {
        validate(score);
        return new Pin(score);
    }

    public int getKnockDownPins() {
        return knockDownPins;
    }

    private static void validate(int pins) {
        if (MIN_PINS > pins || MAX_PINS < pins) {
            throw new IllegalArgumentException(MESSAGE_SCORE_RANGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return getKnockDownPins() == pin.getKnockDownPins();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKnockDownPins());
    }
}
