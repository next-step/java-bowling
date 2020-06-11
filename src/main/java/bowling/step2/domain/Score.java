package bowling.step2.domain;

import bowling.step2.exception.ScoreRangeException;

import java.util.HashMap;
import java.util.Map;

public class Score {
    public static final int MIN_SCORE = 0;
    public static final int MAX_SCORE = 10;

    private static final Map<Integer, Score> FACTORY = new HashMap<>();
    private final int value;

    private Score (int value) {
        this.value = value;
    }

    public static Score stringOf (String value) {
        return valueOf(Integer.parseInt(value));
    }

    public static Score valueOf (int value) {
        validate(value);
        return FACTORY.computeIfAbsent(value, Score::new);
    }

    private static void validate (int value) {
        if (value < MIN_SCORE || value > MAX_SCORE) {
            throw new ScoreRangeException();
        }
    }

    public static Score getStrike () {
        return Score.valueOf(MAX_SCORE);
    }

    public boolean sumIsStrike (Score score) {
        if (score == null) {
            return false;
        }
        return valueOf(value + score.value) == getStrike();
    }

    public int getValue () {
        return value;
    }

    @Override
    public String toString () {
        return String.format("%d", value);
    }

}