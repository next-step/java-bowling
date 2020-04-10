package bowling.domain;

import java.util.List;

import static bowling.Messages.WARNING_FRAME_NOT_ALLOWED_SECOND_WHEN_STRIKE;
import static bowling.Messages.WARNING_FRAME_NOT_ALLOWED_SUM;

public class Frame {
    private static final int SCORE_TEN = 10;
    private static final int SCORE_ZERO = 0;

    private int frameId;
    private Points points;

    public Frame(int firstPoint, int secondPoint) {
        validateSecondWhenFirstTen(firstPoint, secondPoint);
        validateSumIsLessThanTen(firstPoint, secondPoint);
        points = Points.of(firstPoint, secondPoint);
    }

    public static Frame create(RandomGenerator randomGenerator) {
        return new Frame(randomGenerator.getFirstPoint(), randomGenerator.getSecondPoint());
    }

    private void validateSecondWhenFirstTen(int firstPoint, int secondPoint) {
        if (firstPoint == SCORE_TEN && secondPoint != SCORE_ZERO) {
            throw new IllegalArgumentException(WARNING_FRAME_NOT_ALLOWED_SECOND_WHEN_STRIKE);
        }
    }

    private void validateSumIsLessThanTen(int firstPoint, int secondPoint){
        if(firstPoint + secondPoint > SCORE_TEN){
            throw new IllegalArgumentException(WARNING_FRAME_NOT_ALLOWED_SUM);
        }
    }

    public Frame createNextFrame(RandomGenerator randomGenerator) {
        return null;
    }

    public int getFrameId() {
        return frameId;
    }

    public Points getPoints() {
        return points;
    }
}