package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.point.Points;
import bowling.domain.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

import static bowling.Messages.WARNING_FRAME_NOT_ALLOWED_SECOND_WHEN_STRIKE;
import static bowling.Messages.WARNING_FRAME_NOT_ALLOWED_SUM;

public class Frame {
    private static final int SCORE_TEN = 10;
    private static final int SCORE_ZERO = 0;
    private static final int LAST_FRAME_ID = 10;

    private int frameId = 1;
    private Points points;

    public Frame(int firstPoint, int secondPoint) {
        validateSecondWhenFirstTen(firstPoint, secondPoint);
        validateSumIsLessThanTen(firstPoint, secondPoint);
        points = Points.of(firstPoint, secondPoint);
    }

    public Frame(int prevFrameId, int firstPoint, int secondPoint) {
        validateSecondWhenFirstTen(firstPoint, secondPoint);
        validateSumIsLessThanTen(firstPoint, secondPoint);
        points = Points.of(firstPoint, secondPoint);
        this.frameId = prevFrameId + 1;

        if (doesNeedOneMorePoint()) {
            points.addThirdPoint(Point.of(RandomGenerator.getThirdPoint()));
        }
    }

    public static Frame create(RandomGenerator randomGenerator) {
        return new Frame(randomGenerator.getFirstPoint(), randomGenerator.getSecondPoint());
    }

    public static Frames createTenFrames(RandomGenerator randomGenerator) {
        List<Frame> frames = new ArrayList<>();
        Frame currentFrame = Frame.create(randomGenerator);

        for (int i = 0; i < 10; i++) {
            frames.add(currentFrame);
            currentFrame = currentFrame.createNextFrame(randomGenerator);
        }

        return new Frames(frames);
    }

    public Frame createNextFrame(RandomGenerator randomGenerator) {
        return new Frame(this.frameId, randomGenerator.getFirstPoint(), randomGenerator.getSecondPoint());
    }

    private boolean doesNeedOneMorePoint() {
        return frameId == LAST_FRAME_ID && points.isStrikeOrSpare();
    }

    private void validateSecondWhenFirstTen(int firstPoint, int secondPoint) {
        if (firstPoint == SCORE_TEN && secondPoint != SCORE_ZERO) {
            throw new IllegalArgumentException(WARNING_FRAME_NOT_ALLOWED_SECOND_WHEN_STRIKE);
        }
    }

    private void validateSumIsLessThanTen(int firstPoint, int secondPoint) {
        if (firstPoint + secondPoint > SCORE_TEN) {
            throw new IllegalArgumentException(WARNING_FRAME_NOT_ALLOWED_SUM);
        }
    }

    public int getFrameId() {
        return frameId;
    }

    public Points getPoints() {
        return points;
    }

    public FrameResult findResult() {
        return points.findResult();
    }
}