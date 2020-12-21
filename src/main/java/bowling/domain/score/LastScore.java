package bowling.domain.score;

import bowling.domain.BowlType;
import bowling.domain.point.Point;
import bowling.exception.NotHasTurnException;

import java.util.ArrayList;
import java.util.List;

public class LastScore extends Score {


    private Point lastPoint;
    private final Score score;


    protected LastScore(Score score) {
        super(null, null);
        this.lastPoint = null;
        this.score = score;
    }


    public static LastScore initFirst() {
        return new LastScore(BasicScore.initFirst());
    }


    @Override
    public void pitch(Point pitchedPoint) {
        validHasScoreTurn();

        if (score.hasScoreTurn()) {
            score.pitch(pitchedPoint);
            return;
        }
        if (firstPoint == null) {
            firstPoint = pitchedPoint;
            return;
        }
        if (secondPoint == null) {
            secondPoint = pitchedPoint;
            return;
        }

        lastPoint = pitchedPoint;

    }

    private void validHasScoreTurn() {
        if (!hasScoreTurn()) {
            throw new NotHasTurnException();

        }
    }


    @Override
    public boolean hasScoreTurn() {
        if (score.hasScoreTurn()) {
            return true;
        }
        int size = 0;
        if (firstPoint != null) {
            size++;
        }
        if (secondPoint != null) {
            size++;
        }
        if (lastPoint != null) {
            size++;
        }
        return size < getBonusCount();
    }


    @Override
    public BowlType getBowlType() {
        return score.getBowlType();
    }

    @Override
    public List<Point> getPitchedPoint() {
        List<Point> downPoints = new ArrayList<>(score.getPitchedPoint());
        if (firstPoint != null) {
            downPoints.add(firstPoint);
        }
        if (secondPoint != null) {
            downPoints.add(secondPoint);
        }
        if (lastPoint != null) {
            downPoints.add(lastPoint);
        }
        return downPoints;
    }

    @Override
    public int sumPoint() {

        int sum = 0;
        sum += score.sumPoint();
        if (firstPoint != null) {
            sum += firstPoint.getPoint();
        }
        if (secondPoint != null) {
            sum += secondPoint.getPoint();
        }
        if (lastPoint != null) {
            sum += lastPoint.getPoint();
        }
        return sum;

    }


}
