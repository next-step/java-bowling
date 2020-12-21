package bowling.domain.score;

import bowling.domain.BowlType;
import bowling.domain.point.Point;
import bowling.exception.NotHasTurnException;
import bowling.exception.ValidOverPointException;

import java.util.ArrayList;
import java.util.List;

public class BasicScore extends Score {

    private static final int MAX_ROUND = 2;
    private static final int ALL_PITCH_COUNT = 10;
    private static final int FIRST_PITCH = 1;
    private static final int SECOND_PITCH = 2;

    protected BasicScore(Point firstPoint, Point secondPoint) {
        super(firstPoint, secondPoint);
    }


    public static BasicScore initFirst() {
        return new BasicScore(null, null);
    }

    @Override
    public void pitch(Point pitchedPoint) {
        validHasScoreTurn();
        validOverPitch(pitchedPoint);
        if (firstPoint == null) {
            firstPoint = pitchedPoint;
            return;
        }
        if (secondPoint == null) {
            secondPoint = pitchedPoint;
            return;
        }
    }


    private void validHasScoreTurn() {
        if (!hasScoreTurn()) {
            throw new NotHasTurnException();

        }
    }

    private void validOverPitch(Point pitchedPoint) {
        if (sumPoint() + pitchedPoint.getPoint() > ALL_PITCH_COUNT) {
            throw new ValidOverPointException();
        }
    }


    @Override
    public boolean hasScoreTurn() {
        if (secondPoint != null) {
            return false;
        }

        if (sumPoint() == ALL_PITCH_COUNT) {
            return false;
        }

        return true;
    }


    @Override
    public BowlType getBowlType() {
        if (isStrike()) {
            return BowlType.STRIKE;
        }
        if (isSpared()) {
            return BowlType.SPARED;
        }

        if (!hasScoreTurn()) {
            return BowlType.MISS;
        }

        return BowlType.NONE;
    }

    private boolean isStrike() {

        if (firstPoint != null && secondPoint == null && firstPoint.getPoint() == ALL_PITCH_COUNT) {
            return true;
        }

        return false;
    }

    private boolean isSpared() {

        if (firstPoint != null && secondPoint != null && sumPoint() == ALL_PITCH_COUNT) {
            return true;
        }

        return false;
    }

    @Override
    public List<Point> getPitchedPoint() {
        List<Point> as = new ArrayList<>();
        if (firstPoint != null) {
            as.add(firstPoint);
        }
        if (secondPoint != null) {
            as.add(secondPoint);
        }
        return as;
    }

    @Override
    public int sumPoint() {
        int sum = 0;
        if (firstPoint != null) {
            sum += firstPoint.getPoint();
        }
        if (secondPoint != null) {
            sum += secondPoint.getPoint();
        }
        return sum;
    }
}
