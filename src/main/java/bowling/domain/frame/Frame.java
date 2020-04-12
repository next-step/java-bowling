package bowling.domain.frame;

import bowling.domain.RandomGenerator;
import bowling.domain.point.Ordinal;
import bowling.domain.point.Point;
import bowling.domain.point.Points;

import java.util.ArrayList;
import java.util.List;

import static bowling.Messages.WARNING_FRAME_NOT_ALLOWED_SECOND_WHEN_STRIKE;
import static bowling.Messages.WARNING_FRAME_NOT_ALLOWED_SUM;

public class Frame {
    private static final RandomGenerator RANDOM_GENERATOR = new RandomGenerator();
    private static final int SCORE_TEN = 10;
    private static final int SCORE_ZERO = 0;
    private static final int LAST_FRAME_ID = 10;
    private static final int FIRST_FRAME_ID = 1;

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
            points.addThirdPoint(Point.of(RANDOM_GENERATOR.getThirdPoint()));
        }

        if (doesNeedTwoMorePoint()) {
            points.addThirdPoint(Point.of(RANDOM_GENERATOR.getThirdPointForStrike()));
            points.addFourthPoint(Point.of(RANDOM_GENERATOR.getFourthPointForStrike()));
        }
    }

    public static Frame create(RandomGenerator randomGenerator) {
        return new Frame(randomGenerator.getFirstPoint(), randomGenerator.getSecondPoint());
    }

    public static Frames createTenFrames(RandomGenerator randomGenerator) {
        List<Frame> frames = new ArrayList<>();
        Frame currentFrame = Frame.create(randomGenerator);

        for (int i = FIRST_FRAME_ID; i <= LAST_FRAME_ID; i++) {
            frames.add(currentFrame);
            currentFrame = currentFrame.createNextFrame(randomGenerator);
        }

        return new Frames(frames);
    }

    public Frame createNextFrame(RandomGenerator randomGenerator) {
        return new Frame(this.frameId, randomGenerator.getFirstPoint(), randomGenerator.getSecondPoint());
    }

    private boolean doesNeedOneMorePoint() {
        return frameId == LAST_FRAME_ID && points.isSpare();
    }

    private boolean doesNeedTwoMorePoint() {
        return frameId == LAST_FRAME_ID && points.isStrike();
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

    public int getFirstPoint() {
        return points.getFirstPoint();
    }

    public int getSecondPoint() {
        return points.getSecondPoint();
    }

    public int getThirdPoint() {
        return points.getThirdPoint();
    }

    public int getFourthPoint() {
        return points.getFourthPoint();
    }

    public FrameResult findResult() {
        return points.findResult();
    }

    public boolean isStrike() {
        return FrameResult.STRIKE.equals(points.findResult());
    }

    public boolean isSpare() {
        return FrameResult.SPARE.equals(points.findResult());
    }

    public boolean isMiss() {
        return FrameResult.MISS.equals(points.findResult());
    }

    public boolean isGutter() {
        return FrameResult.GUTTER.equals(points.findResult());
    }

    public int getPointAtOrdinal(Ordinal ordinal) {
        if (Ordinal.SECOND.equals(ordinal)) {
            return points.getSecondPoint();
        }

        if (Ordinal.THIRD.equals(ordinal)) {
            return points.getThirdPoint();
        }

        if (Ordinal.FOURTH.equals(ordinal)) {
            return points.getFourthPoint();
        }

        return points.getFirstPoint();
    }

    public boolean containsOrdinal(Ordinal ordinal) {
        return points.containsOrdinal(ordinal);
    }
}