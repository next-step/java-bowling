package domain;

import View.BowlingScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NormalScore implements BowlingScore {
    private static final boolean FIRST_IS_NOT_SPARE = Boolean.FALSE;
    private static final int FRAME_SIZE = 2;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int BOWL_ONCE = 1;
    public static final int ZERO = 0;
    public static final int STRIKE = 10;

    private final List<Point> points;
    private final int scoreNumber;

    public NormalScore(int scoreNumber) {
        this.points = new ArrayList<>();
        this.scoreNumber = scoreNumber;
    }

    @Override
    public boolean bowl(int score) {
        if (!nowBowlable()) {
            return false;
        }

        if (isOverScore(score)) {
            throw new IllegalArgumentException("한 프레임의 합은 10점을 초과할 수 없습니다.");
        }

        Point point = Point.bowl(score);
        points.add(point);

        return true;
    }

    @Override
    public boolean nowBowlable() {
        if (getPointSize() == FRAME_SIZE) {
            return false;
        }
        if (isStrike()) {
            return false;
        }
        return true;
    }

    private boolean isOverScore(int currentScore) {
        if (getPointSize() < BOWL_ONCE) {
            return false;
        }
        int lastScore = getPoint(getPointSize() - 1);
        return lastScore + currentScore > STRIKE ? true : false;
    }

    @Override
    public int sumScore() {
        return points.stream()
                .mapToInt(point -> point.getPoint())
                .sum();
    }

    protected int getPoint(int position) {
        Optional<Point> maybePoint = Optional.ofNullable(points.get(position));
        Point point = maybePoint.orElseThrow(IllegalStateException::new);
        return point.getPoint();
    }

    public boolean isStrike() {
        if (getPointSize() < BOWL_ONCE) {
            return false;
        }
        return getPoint(FIRST) == STRIKE ? true : false;
    }

    public boolean isSpare() {
        if (getPointSize() <= BOWL_ONCE) {
            return false;
        }
        if (!isStrike() && (getPoint(FIRST) + getPoint(SECOND)) == STRIKE) {
            return true;
        }
        return false;
    }

    @Override
    public int getPointSize() {
        return points.size();
    }

    public boolean isNullablePoint(int position) {
        if (getPointSize() <= position) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public int getFrameNumber() {
        return scoreNumber;
    }

    @Override
    public String getResult() {
        final String SCORE_CONNECTOR = "|";

        StringBuilder builder = new StringBuilder();
        builder.append(PointName.valueOfPointName(getPoint(FIRST), FIRST_IS_NOT_SPARE));

        if (getPointSize() == FRAME_SIZE) {
            builder.append(SCORE_CONNECTOR);
            builder.append(PointName.valueOfPointName(getPoint(SECOND), isSpare()));
        }

        return String.format("%-4s", builder.toString());
    }
}
