package step2.domain;

import step2.exception.NotExistsNextFrameException;
import step2.strategy.PitchesStrategy;
import step2.type.ResultPitchesType;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static step2.domain.BowlingPoint.*;
import static step2.type.ResultPitchesType.STRIKE;

public class FinalFrame implements Frame {
    public static final String ERROR_CURRENT_FRAME_IS_FINAL = "해당 프레임이 마지막 프레임입니다.";
    public static final String WALL_DELIMITER = "|";

    private final int frameNo;
    private boolean completed;
    private BowlingPoint firstPoint;
    private BowlingPoint secondPoint;
    private BowlingPoint thirdPoint;

    public FinalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.completed = false;
    }

    @Override
    public int pitches(PitchesStrategy strategy) {
        if (Objects.isNull(firstPoint)) {
            firstPoint = BowlingPoint.of(strategy.shot(0));
            return firstPoint.getPoint();
        }
        if (Objects.isNull(secondPoint)) {
            secondPoint = BowlingPoint.of(strategy.shot(getAllowStrategyPoint(firstPoint)), firstPoint.getPoint());
            updateComplete();
            return secondPoint.getPoint();
        }
        if (Objects.isNull(thirdPoint) && getCurrentScore() >= 10) {
            thirdPoint = BowlingPoint.of(strategy.shot(getAllowStrategyPoint(secondPoint)), secondPoint.getPoint());
            completed = true;
            return thirdPoint.getPoint();
        }

        throw new IllegalArgumentException("더 이상 던질 수 없습니다.");
    }

    private int getAllowStrategyPoint(BowlingPoint point) {
        int getPoint = point.getPoint();
        return getPoint == 10 ? 0 : getPoint;
    }

    private void updateComplete() {
        if (firstPoint.getPoint() + secondPoint.getPoint() < 10) {
            completed = true;
        }
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public int getScore() {
        return getCurrentScore();
    }

    @Override
    public int getScore(ResultPitchesType prevType) {
        if (STRIKE.equals(prevType)) {
            return getScoreByPoint(firstPoint) + getScoreByPoint(secondPoint);
        }
        return getScoreByPoint(firstPoint);
    }


    @Override
    public int getCurrentScore() {
        return Stream.of(firstPoint, secondPoint, thirdPoint)
                .map(this::getScoreByPoint)
                .reduce(0, Integer::sum);
    }

    @Override
    public Frame next() {
        throw new NotExistsNextFrameException(ERROR_CURRENT_FRAME_IS_FINAL);
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String getResultString() {
        return Stream.of(firstPoint, secondPoint, thirdPoint)
                .filter(point -> Objects.nonNull(point)
                        && point.isCompleted()
                        && !point.getMark().equals(NO_MARK))
                .map(BowlingPoint::getMark)
                .collect(Collectors.joining(WALL_DELIMITER));
    }

    @Override
    public boolean isFinished() {
        return completed;
    }

    private int getScoreByPoint(BowlingPoint point) {
        return Objects.nonNull(point) ? point.getPoint() : 0;

    }
}
