package bowling.domain.score;

import bowling.domain.BowlType;
import bowling.domain.point.Point;
import bowling.exception.NotHasTurnException;

import java.util.ArrayList;
import java.util.List;

public class LastScore extends Score {


    private final Score score;

    protected LastScore(Score score) {
        super(new ArrayList<>());
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
        this.points.add(pitchedPoint);
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
        return this.points.size() < getBonusCount();
    }


    @Override
    public BowlType getBowlType() {
        return score.getBowlType();
    }

    @Override
    public List<Point> getPitchedPoint() {
        List<Point> downPoints = new ArrayList<>(score.getPitchedPoint());
        downPoints.addAll(points);
        return downPoints;
    }

    @Override
    public int sumPoint() {
        return score.sumPoint() + points.stream()
                .map(Point::getPoint)
                .reduce(0, Integer::sum);
    }

}
