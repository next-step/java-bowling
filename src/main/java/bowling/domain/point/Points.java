package bowling.domain.point;

import bowling.domain.frame.OverThrowBallException;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreStatus;

import java.util.ArrayList;
import java.util.List;

public class Points {
    private static final int FIRST_POINT_INDEX = 0;
    private static final int SECOND_POINT_INDEX = 1;
    private static final int THIRD_POINT_INDEX = 2;

    private static final int NONE_TRY_COUNT = 1;
    private static final int FIRST_TRY_COUNT = 1;
    private static final int SECOND_TRY_COUNT = 2;

    private static final int DOUBLE_STRIKE_POINT = 20;
    private static final int SPARE_POINT = 10;
    private static final int GUTTER_POINT = 0;

    private List<Point> points;
    private ScoreStatus scoreStatus;

    public Points() {
        this.points = new ArrayList<>();
        this.scoreStatus = ScoreStatus.NONE;
    }

    public Points(List<Point> points) {
        this.points = points;
        this.scoreStatus = ScoreStatus.NONE;
    }

    public Points(List<Point> points, ScoreStatus scoreStatus) {
        this.points = points;
        this.scoreStatus = scoreStatus;
    }

    public Points addPoint(Point point) {
        validateLeftPoint(point);
        points.add(point);
        this.scoreStatus = reScoring();
        return new Points(points, scoreStatus);
    }

    public ScoreStatus getScoreStatus() {
        scoreStatus = reScoring();
        return scoreStatus;
    }

    public boolean isScore(ScoreStatus status) {
        return this.scoreStatus.equals(status);
    }

    public boolean isTryCount(int count) {
        return points.size() == count;
    }

    public boolean isTryOver(int maxTryableCount) {
        return points.size() >= maxTryableCount;
    }

    public int getSum() {
        return points.stream()
                .mapToInt(Point::getPoint)
                .sum();
    }

    public Score makeScore() {
        if (ScoreStatus.STRIKE.equals(scoreStatus)) {
            return Score.ofStrike();
        }
        if (ScoreStatus.SPARE.equals(scoreStatus)) {
            return Score.ofSpare();
        }
        return Score.ofMiss(getSum());
    }

    public Point getFirstPoint() {
        return points.get(FIRST_POINT_INDEX);
    }

    public Point getSecondPoint() {
        return points.get(SECOND_POINT_INDEX);
    }

    public Point getThirdPoint() {
        return points.get(THIRD_POINT_INDEX);
    }

    public boolean isThrowableForNormalFrame() {
        if (isTryOver(SECOND_TRY_COUNT) || ScoreStatus.STRIKE.equals(scoreStatus)) {
            return false;
        }
        return true;
    }

    public boolean isThrowableForFinalFrame() {
        if (isTryCount(NONE_TRY_COUNT) || isTryCount(FIRST_TRY_COUNT)) {
            return true;
        }
        if (isTryCount(SECOND_TRY_COUNT) && (ScoreStatus.STRIKE.equals(scoreStatus) || ScoreStatus.SPARE.equals(scoreStatus))) {
            return true;
        }
        return false;
    }

    private ScoreStatus reScoring() {
        if (isFirstStrike() || isDoubleStrike()) {
            return ScoreStatus.STRIKE;
        }
        if (isSpare()) {
            return ScoreStatus.SPARE;
        }
        if (isGutter()) {
            return ScoreStatus.GUTTER;
        }
        return ScoreStatus.MISS;
    }

    private boolean isFirstStrike() {
        return getFirstPoint().isMaxPoint();
    }

    private boolean isDoubleStrike() {
        return isTryCount(SECOND_TRY_COUNT) && getSum() == DOUBLE_STRIKE_POINT;
    }

    private boolean isSpare() {
        return isTryCount(SECOND_TRY_COUNT) && getSum() == SPARE_POINT;
    }

    private boolean isGutter() {
        return getSum() == GUTTER_POINT;
    }

    private void validateLeftPoint(Point point) {
        int leftPint = getLeftPoint();
        if (point.getPoint() > leftPint) {
            throw new OverThrowBallException("다시 입력해주세요(남은 핀: " + leftPint + ")");
        }
    }

    private int getLeftPoint() {
        if (isTryCount(FIRST_TRY_COUNT) && !isFirstStrike()) {
            return Point.MAX_POINT - getSum();
        }
        return Point.MAX_POINT;
    }
}
