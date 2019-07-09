package domain;

import View.BowlingScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.NormalScore.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class FinalScore implements BowlingScore {
    private static final int FRAME_SIZE = 3;

    private final List<Point> points;

    public FinalScore() {
        this.points = new ArrayList<>();
    }

    @Override
    public boolean bowl(int point) {
        if (!nowBowlable()) {
            return FALSE;
        }

        if (isOverPoint(point)) {
            throw new IllegalArgumentException("최근 두 번의 투구 합은 10점을 초과할 수 없습니다.");
        }

        Point newPoint = Point.bowl(point);
        points.add(newPoint);

        return TRUE;
    }

    @Override
    public boolean nowBowlable() {
        return (isAbleToBonus() || getPointExistCount() == FRAME_SIZE) ? FALSE : TRUE;
    }

    @Override
    public boolean isOverPoint(int currentScore) {
        int lastPosition = getPointExistCount() - 1;
        if (getPointExistCount() < BOWL_ONCE) {
            return FALSE;
        }
        return !isStrike(lastPosition) &&
                !isSpare(lastPosition) &&
                (getPointScore(lastPosition) + currentScore) > STRIKE;
    }

    @Override
    public int sumScore() {
        return points.stream()
                .mapToInt(point -> point.getPointScore())
                .sum();
    }

    @Override
    public String framePoint() {
        final String BLANK_FRAME = "     ";
        final String SCORE_CONNECTOR = "|";

        if (getPointExistCount() == 0) {
            return BLANK_FRAME;
        }

        String score = IntStream.range(0, getPointExistCount()).boxed()
                .map(count -> PointName.valueOfPointName(getPointScore(count), isSpare(count)))
                .map(result -> SCORE_CONNECTOR + result)
                .collect(Collectors.joining());
        score = score.substring(SECOND);
        return String.format("%-5s", score);
    }

    @Override
    public int getPointScore(int position) {
        Optional<Point> maybePoint = Optional.ofNullable(points.get(position));
        Point point = maybePoint.orElseThrow(IllegalStateException::new);
        return point.getPointScore();
    }

    @Override
    public int getPointExistCount() {
        return points.size();
    }

    private boolean isStrike(int position) {
        return getPointScore(position) == STRIKE ? TRUE : FALSE;
    }

    private boolean isSpare(int position) {
        if (position < SECOND) {
            return FALSE;
        }
        if (!isStrike(position) && (getPointScore(position - 1) + getPointScore(position)) == STRIKE) {
            return TRUE;
        }
        return FALSE;
    }

    private boolean isAbleToBonus() {
        int lastPosition = getPointExistCount() - 1;
        if (getPointExistCount() < BOWL_TWICE) {
            return FALSE;
        }
        return !isStrike(lastPosition - 1) && !isStrike(lastPosition) && !isSpare(lastPosition);
    }
}
