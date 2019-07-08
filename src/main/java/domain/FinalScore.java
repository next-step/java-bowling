package domain;

import View.BowlingScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.NormalScore.*;

public class FinalScore implements BowlingScore {
    private static final int FRAME_SIZE = 3;
    private static final int BOWL_TWICE = 2;

    private final List<Point> points;

    public FinalScore() {
        this.points = new ArrayList<>();
    }

    @Override
    public boolean bowl(int score) {
        if (!nowBowlable()) {
            return false;
        }

        if (isOverPoint(score)) {
            throw new IllegalArgumentException("최근 두 번의 투구 합은 10점을 초과할 수 없습니다.");
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
        if (isAbleToBonus()) {
            return false;
        }
        return true;
    }

    private boolean isOverPoint(int currentScore) {
        int lastPosition = getPointSize() - 1;
        if (getPointSize() < BOWL_ONCE) {
            return false;
        }
        return !isStrike(lastPosition) && !isSpare(lastPosition) && (getPoint(lastPosition) + currentScore) > STRIKE;
    }

    private boolean isAbleToBonus() {
        int lastPosition = getPointSize() - 1;
        if (getPointSize() < BOWL_TWICE) {
            return false;
        }
        return !isStrike(lastPosition - 1) && !isStrike(lastPosition) && !isSpare(lastPosition);
    }

    @Override
    public int sumScore() {
        return points.stream()
                .mapToInt(point -> point.getPoint())
                .sum();
    }

    public boolean isStrike(int position) {
        return getPoint(position) == STRIKE ? true : false;
    }

    public boolean isSpare(int position) {
        if (position < SECOND) {
            return false;
        }
        if (!isStrike(position) && (getPoint(position - 1) + getPoint(position)) == STRIKE) {
            return true;
        }
        return false;
    }

    @Override
    public int getPointSize() {
        return points.size();
    }

    protected int getPoint(int position) {
        Optional<Point> maybePoint = Optional.ofNullable(points.get(position));
        Point point = maybePoint.orElseThrow(IllegalStateException::new);
        return point.getPoint();
    }

    @Override
    public String getResult() {
        final String BLANK_FRAME = "     ";
        final String SCORE_CONNECTOR = "|";

        if (getPointSize() == 0) {
            return BLANK_FRAME;
        }

        String score = IntStream.range(0, getPointSize()).boxed()
                .map(count -> PointName.valueOfPointName(getPoint(count), isSpare(count)))
                .map(result -> SCORE_CONNECTOR + result)
                .collect(Collectors.joining());
        score = score.substring(SECOND);
        return String.format("%-5s", score);
    }
}
