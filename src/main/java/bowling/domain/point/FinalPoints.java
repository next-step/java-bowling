package bowling.domain.point;

import bowling.domain.Playable;
import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FinalPoints implements Playable {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

    private boolean hasExtra;
    private List<Point> points;

    public FinalPoints() {
        this.hasExtra = false;
        this.points = points();
    }

    private List<Point> points() {
        return IntStream.rangeClosed(FIRST, THIRD)
                .mapToObj(v -> new Point())
                .collect(Collectors.toList());
    }

    @Override
    public boolean ended() {
        if (hasExtra) {
            return points.get(THIRD).played();
        }
        return points.get(SECOND).played();
    }

    @Override
    public void throwBall(int point) {
        if (hasExtra) {
            throwExtraBall(point);
            return;
        }
        throwNormalBall(point);
        giveExtraChancesIfNeeded();
    }

    private void throwExtraBall(int point) {
        if (!points.get(SECOND).played()) {
            points.get(SECOND).throwBall(point);
            return;
        }
        points.get(THIRD).throwBall(point);
    }

    private void throwNormalBall(int point) {
        if (!points.get(FIRST).played()) {
            points.get(FIRST)
                    .throwBall(point);
            return;
        }
        if (!valid(points.get(FIRST), point)) {
            throw new CustomException(ErrorCode.INVALID_POINT_SUM);
        }
        points.get(SECOND)
                .throwBall(point);
    }

    private boolean valid(Point prePoint, int curPoint) {
        Point point = new Point();
        point.throwBall(curPoint);
        return !prePoint.overs(point);
    }

    private void giveExtraChancesIfNeeded() {
        if (points.get(FIRST)
                .striked()) {
            hasExtra = true;
            return;
        }
        if (points.get(SECOND).spared(points.get(FIRST))) {
            hasExtra = true;
        }
    }

    @Override
    public boolean striked() {
        return points.get(FIRST).striked();
    }

    @Override
    public boolean spared() {
        return points.get(FIRST).spared(points.get(SECOND));
    }

    public boolean hasExtra() {
        return hasExtra;
    }

    public List<Point> getPoints() {
        return points;
    }
}
