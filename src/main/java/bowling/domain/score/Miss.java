package bowling.domain.score;

import bowling.domain.point.Point;

public class Miss extends Score {
    public Miss(int point) {
        super(point);
    }

    @Override
    public Score nextScore(int point) {
        if (isStrikePoint(this.point.getPoint() + point)) {
            return new Spare(point);
        }
        return getMissOrGutterScore(point);
    }

    @Override
    public boolean isEqualScoreType(ScoreType scoreType) {
        return scoreType.equals(ScoreType.MISS);
    }

    @Override
    public String getScore() {
        return ScoreType.MISS.pointToScore(point.getPoint());
    }
}
