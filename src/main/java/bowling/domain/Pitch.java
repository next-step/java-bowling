package bowling.domain;

import java.util.Objects;

/**
 * Created By mand2 on 2020-12-18.
 */
public class Pitch {

    public static final int MIN_PINS = 0;
    public static final int MAX_PINS = 10;
    public static final String MESSAGE_SCORE_RANGE = "투구 점수는 0~10 사이입니다";
    
    private final int score;

    private Pitch(int score) {
        this.score = score;
    }

    public static Pitch from(int score) {
        validate(score);
        return new Pitch(score);
    }

    public int getScore() {
        return score;
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
        Pitch pitch = (Pitch) o;
        return getScore() == pitch.getScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getScore());
    }
}
