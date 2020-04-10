package bowling.domain;

import static bowling.Messages.WARNING_FRAME_NOT_ALLOWED_SECOND_WHEN_STRIKE;

public class Frame {
    private static final int SCORE_TEN = 10;
    private static final int SCORE_ZERO = 0;

    private Point firstPoint;
    private Point secondPoint;

    public Frame(int firstPoint, int secondPoint) {
        validateSecondWhenFirstTen(firstPoint, secondPoint);
        this.firstPoint = Point.of(firstPoint);
        this.secondPoint = Point.of(secondPoint);
    }

    private void validateSecondWhenFirstTen(int firstPoint, int secondPoint) {
        if (isFirstTen(firstPoint) && secondPoint != SCORE_ZERO) {
            throw new IllegalArgumentException(WARNING_FRAME_NOT_ALLOWED_SECOND_WHEN_STRIKE);
        }
    }

    private boolean isFirstTen(int firstScore) {
        return firstScore == SCORE_TEN;
    }
}