package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FinalScore {
    private static final int FINAL_FRAME_SIZE = 3;
    private static final int STRIKE = 10;
    private static final int BOWL_ONCE = 1;
    private static final int BOWL_TWICE_INDEX = 1;
    private static final int BOWL_TWICE = 2;

    private final List<Point> points;

    public FinalScore() {
        this.points = new ArrayList<>();
    }

    public int bowl(int score) {
        if (points.size() == FINAL_FRAME_SIZE) {
            throw new IllegalStateException("사전 정의된 size수 만큼 투구 가능합니다.");
        }

        if (isOverPoint(score)) {
            throw new IllegalArgumentException("최근 두 번의 투구 합은 10점을 초과할 수 없습니다.");
        }

        if (isAbleToBonus()) {
            throw new IllegalStateException("보너스 투구는 두 번째 투구가 커버여야 가능합니다.");
        }

        Point point = Point.bowl(score);
        points.add(point);

        return sumScore();
    }

    private boolean isOverPoint(int currentScore) {
        int lastPosition = points.size() - 1;
        if (points.size() < BOWL_ONCE) {
            return false;
        }
        return !isStrike(lastPosition) && !isSpare(lastPosition) && (getPoint(lastPosition) + currentScore) > STRIKE;
    }

    private boolean isAbleToBonus() {
        int lastPosition = points.size() - 1;
        if (points.size() < BOWL_TWICE) {
            return false;
        }
        return !isStrike(lastPosition - 1) && !isStrike(lastPosition) && !isSpare(lastPosition);
    }

    private int sumScore() {
        return points.stream()
                .mapToInt(point -> point.getPoint())
                .sum();
    }

    public boolean isStrike(int position) {
        return getPoint(position) == STRIKE ? true : false;
    }

    public boolean isSpare(int position) {
        if (position < BOWL_TWICE_INDEX) {
            return false;
        }
        if (!isStrike(position) && (getPoint(position - 1) + getPoint(position)) == STRIKE) {
            return true;
        }
        return false;
    }

    private int getPoint(int position) {
        Point point = points.get(position);
        return point.getPoint();
    }

    public String getScore() {
        final String SCORE_CONNECTOR = "|";

        String score = IntStream.range(0, FINAL_FRAME_SIZE)
                .boxed()
                .map(count -> PointName.valueOfPointName(getPoint(count), isSpare(count)))
                .map(result -> result + SCORE_CONNECTOR)
                .collect(Collectors.joining());
        return score.substring(0, score.length() - 2);
    }
}
