package bowling.domain.score;

public class NomalScore extends Score{
    public NomalScore(int point) {
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
        if (isGutterPoint(point.getPoint())) {
            return scoreType.equals(ScoreType.GUTTER);
        }
        return scoreType.equals(ScoreType.MISS);
    }

    @Override
    public String getScore() {
        if (isGutterPoint(point.getPoint())) {
            return ScoreType.GUTTER.pointToScore(point.getPoint());
        }
        return ScoreType.MISS.pointToScore(point.getPoint());
    }
}
