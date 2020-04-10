package bowling.domain;

import static bowling.Messages.WARNING_FRAME_NOT_ALLOWED_SECOND_WHEN_STRIKE;

public class Frame {
    private static final int SCORE_TEN = 10;
    private static final int SCORE_ZERO = 0;

    private Point firstPoint;
    private Point secondPoint;

    public Frame(int firstScore, int secondScore) {
        validateSecondWhenFirstTen(firstScore, secondScore);
        this.firstPoint = Point.of(firstScore);
        this.secondPoint = Point.of(secondScore);
    }

    private void validateSecondWhenFirstTen(int firstScore, int secondScore) {
        if (isFirstTen(firstScore) && secondScore != SCORE_ZERO) {
            throw new IllegalArgumentException(WARNING_FRAME_NOT_ALLOWED_SECOND_WHEN_STRIKE);
        }
    }

    private boolean isFirstTen(int firstScore) {
        return firstScore == SCORE_TEN;
    }
}