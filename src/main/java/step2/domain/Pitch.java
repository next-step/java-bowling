package step2.domain;

import step2.domain.state.Strike;

public class Pitch {

    public static final int MIN_SCORE = 0;
    public static final int MAX_SCORE = 10;
    private static final String GUTTER_SYMBOL = "-";

    private final int score;

    private Pitch(int score) {
        validate(score);
        this.score = score;
    }

    private void validate(int score) {
        validateBelowZero(score);
        validateOverTen(score);
    }

    private void validateBelowZero(int score) {
        if (score < MIN_SCORE) {
            throw new IllegalArgumentException("한번에 쓰러뜨릴 수 있는 핀은 0개 이상입니다.");
        }
    }

    private void validateOverTen(int score) {
        if (score > MAX_SCORE) {
            throw new IllegalArgumentException("한번에 쓰러뜨릴 수 있는 핀은 10개 이하입니다.");
        }
    }

    public static Pitch from(int score) {
        return new Pitch(score);
    }

    public int getScore() {
        return score;
    }

    public boolean isStrike() {
        return score == MAX_SCORE;
    }

    public boolean isGutter() {
        return score == MIN_SCORE;
    }

    @Override
    public String toString() {
        if (isGutter()) {
            return GUTTER_SYMBOL;
        }

        if (isStrike()) {
            return Strike.SYMBOL;
        }
        return "" + score;
    }
}
