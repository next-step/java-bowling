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
    private static final int SCORE_ZERO = 0;
    private static final int SCORE_TEN = 10;
    private static final int FRAME_ID_ZERO = 0;
    private static final int FRAME_ID_FIRST = 1;
    private static final int FRAME_ID_NINETH = 9;
    private static final int FRAME_ID_FINAL = 10;
    private static final int INCREMENT_FOR_NEXT_ID = 1;

    private int frameId;
    private Points points;

    public Frame(int prevFrameId, int firstPoint, int secondPoint) {
        validateSecondWhenFirstTen(firstPoint, secondPoint);
        validateSumIsLessThanTen(firstPoint, secondPoint);
        points = Points.of(firstPoint, secondPoint);
        this.frameId = prevFrameId + INCREMENT_FOR_NEXT_ID;

        addPointForSpare();
        addPointsForStrike();
    }

    public static Frame create() {
        return new Frame(FRAME_ID_ZERO, RANDOM_GENERATOR.getFirstPoint(), RANDOM_GENERATOR.getSecondPoint());
    }

    public static Frames createTenFrames() {
        List<Frame> frames = new ArrayList<>();
        Frame currentFrame = Frame.create();

        for (int i = FRAME_ID_FIRST; i <= FRAME_ID_FINAL; i++) {
            frames.add(currentFrame);
            currentFrame = currentFrame.createNextFrame();
        }

        return Frames.of(frames);
    }

    public Frame createNextFrame() {
        return new Frame(this.frameId, RANDOM_GENERATOR.getFirstPoint(), RANDOM_GENERATOR.getSecondPoint());
    }

    public int getPointSumOnlyThisFrame() {
        return points.sum();
    }

    public boolean isFirstFrame() {
        return frameId == FRAME_ID_FIRST;
    }

    public boolean isFinalFrame() {
        return frameId == FRAME_ID_FINAL;
    }

    public boolean isGutterOrMiss() {
        return (isMiss() || isGutter());
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
        return Ordinal.getPoint(ordinal, points);
    }

    public boolean containsOrdinal(Ordinal ordinal) {
        return points.containsOrdinal(ordinal);
    }

    public boolean isNineth() {
        return frameId == FRAME_ID_NINETH;
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

    private boolean doesNeedOneMorePoint() {
        return (isFinalFrame() && isSpare());
    }

    private boolean doesNeedTwoMorePoint() {
        return (isFinalFrame() && isStrike());
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

    private void addPointForSpare() {
        if (doesNeedOneMorePoint()) {
            points.addThirdPoint(Point.of(RANDOM_GENERATOR.getThirdPoint()));
        }
    }

    private void addPointsForStrike() {
        if (doesNeedTwoMorePoint()) {
            points.addThirdPointForStrike(Point.of(RANDOM_GENERATOR.getThirdPointForStrike()));
            points.addFourthPointForStrike(Point.of(RANDOM_GENERATOR.getFourthPointForStrike()));
        }
    }
}