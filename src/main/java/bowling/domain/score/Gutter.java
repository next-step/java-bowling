package bowling.domain.score;

public class Gutter extends Score {
    public Gutter(int point) {
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
        return scoreType.equals(ScoreType.GUTTER);
    }

    @Override
    public String getScore() {
        return ScoreType.GUTTER.pointToScore(point.getPoint());
    }
}
