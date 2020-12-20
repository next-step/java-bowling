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

    protected BasicScore(List<Point> points) {
        super(points);
    }


    public static BasicScore initFirst() {
        return new BasicScore(new ArrayList<>());
    }

    @Override
    public void pitch(Point pitchedPoint) {
        validHasScoreTurn();
        validOverPitch(pitchedPoint);
        points.add(pitchedPoint);
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
        if (this.points.size() == MAX_ROUND) {
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

        if (this.points.size() == FIRST_PITCH && this.points.get(0).getPoint() == ALL_PITCH_COUNT) {
            return true;
        }

        return false;
    }

    private boolean isSpared() {

        if (this.points.size() == SECOND_PITCH && sumPoint() == ALL_PITCH_COUNT) {
            return true;
        }

        return false;
    }

    @Override
    public List<Point> getPitchedPoint() {
        return new ArrayList<>(this.points);
    }

    @Override
    public int sumPoint() {
        return this.points.stream()
                .map(Point::getPoint)
                .reduce(0, Integer::sum);
    }
}
