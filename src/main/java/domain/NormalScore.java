package domain;

import View.BowlingScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class NormalScore implements BowlingScore {
    private static final boolean FIRST_IS_NOT_SPARE = FALSE;
    private static final int FRAME_SIZE = 2;
    public static final int ZERO = 0;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int BOWL_ONCE = 1;
    public static final int BOWL_TWICE = 2;
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
            return FALSE;
        }

        if (isOverScore(score)) {
            throw new IllegalArgumentException("한 프레임의 합은 10점을 초과할 수 없습니다.");
        }

        Point point = Point.bowl(score);
        points.add(point);

        return TRUE;
    }

    @Override
    public boolean nowBowlable() {
        return isStrike() || getPointExistCount() == BOWL_TWICE ? FALSE : TRUE;
    }

    private boolean isOverScore(int currentScore) {
        if (getPointExistCount() < BOWL_ONCE) {
            return FALSE;
        }
        int lastScore = getPointScore(getPointExistCount() - 1);
        return lastScore + currentScore > STRIKE ? TRUE : FALSE;
    }

    @Override
    public int sumScore() {
        return points.stream()
                .mapToInt(point -> point.getPointScore())
                .sum();
    }

    protected boolean isStrike() {
        if (getPointExistCount() < BOWL_ONCE) {
            return FALSE;
        }
        return getPointScore(FIRST) == STRIKE ? TRUE : FALSE;
    }

    protected boolean isSpare() {
        if (getPointExistCount() < BOWL_TWICE) {
            return FALSE;
        }
        if (!isStrike() && (getPointScore(FIRST) + getPointScore(SECOND)) == STRIKE) {
            return TRUE;
        }
        return FALSE;
    }

    protected boolean isNullablePoint(int pointPosition) {
        return getPointExistCount() <= pointPosition ? Boolean.TRUE : FALSE;
    }

    @Override
    public String framePoint() {
        final String SCORE_CONNECTOR = "|";

        StringBuilder builder = new StringBuilder();
        builder.append(PointName.valueOfPointName(getPointScore(FIRST), FIRST_IS_NOT_SPARE));

        if (getPointExistCount() == FRAME_SIZE) {
            builder.append(SCORE_CONNECTOR);
            builder.append(PointName.valueOfPointName(getPointScore(SECOND), isSpare()));
        }

        return String.format("%-4s", builder.toString());
    }

    protected int getPointScore(int position) {
        Optional<Point> maybePoint = Optional.ofNullable(points.get(position));
        Point point = maybePoint.orElseThrow(IllegalStateException::new);
        return point.getPointScore();
    }

    protected int getFrameNumber() {
        return scoreNumber;
    }

    @Override
    public int getPointExistCount() {
        return points.size();
    }
}
