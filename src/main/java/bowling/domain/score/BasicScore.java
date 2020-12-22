package bowling.domain.score;

import bowling.domain.point.Point;
import bowling.exception.NotHasTurnException;
import bowling.exception.ValidOverPointException;

import java.util.ArrayList;
import java.util.List;

public class BasicScore extends Score {

    private static final int ALL_PITCH_COUNT = 10;

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
        secondPoint = pitchedPoint;

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
    public ScoreType getBowlType() {
        return ScoreType.valueOf(this);
    }


    @Override
    public List<Point> getPitchedPoint() {
        List<Point> pitchedPoints = new ArrayList<>();
        if (firstPoint != null) {
            pitchedPoints.add(firstPoint);
        }
        if (secondPoint != null) {
            pitchedPoints.add(secondPoint);
        }
        return pitchedPoints;
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

    @Override
    public Point getFirstPoint() {
        return firstPoint;
    }

    @Override
    public Point getSecondPoint() {
        return secondPoint;
    }
}
