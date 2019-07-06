package domain;

import java.util.ArrayList;
import java.util.List;

public class NormalScore {
    private static final int FRAME_SIZE = 2;
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int BOWL_ONCE = 1;
    public static final int STRIKE = 10;

    private final List<Point> points;

    public NormalScore() {
        this.points = new ArrayList<>();
    }

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
        return lastScore + currentScore > 10 ? true : false;
    }

    public int sumScore() {
        return points.stream()
                .mapToInt(point -> point.getPoint())
                .sum();
    }

    public boolean isStrike() {
        if (getPointSize() < BOWL_ONCE) {
            return false;
        }
        return getPoint(FIRST) == STRIKE ? true : false;
    }

    public boolean isSpare(int position) {
        if (position < SECOND) {
            return false;
        }
        if (!isStrike() && (getPoint(position - 1) + getPoint(position)) == STRIKE) {
            return true;
        }
        return false;
    }

    public int getPointSize() {
        return points.size();
    }

    private int getPoint(int position) {
        Point point = points.get(position);
        return point.getPoint();
    }

    public String getResult() {
        final String SCORE_CONNECTOR = "|";

        StringBuilder builder = new StringBuilder();
        builder.append(PointName.valueOfPointName(getPoint(FIRST), isSpare(FIRST)));

        if (getPointSize() == FRAME_SIZE) {
            builder.append(SCORE_CONNECTOR);
            builder.append(PointName.valueOfPointName(getPoint(SECOND), isSpare(SECOND)));
        }

        return String.format("%-4s", builder.toString());
    }
}
