package bowling.domain.score;

public class NomalScore extends Score{
    public NomalScore(int point, ScoreType scoreType) {
        super(point, scoreType);
    }

    public static NomalScore of(int point) {
        if (isGutterPoint(point)) {
            return new NomalScore(point, ScoreType.GUTTER);
        }
        return new NomalScore(point, ScoreType.MISS);
    }

    @Override
    public Score nextScore(int point) {
        if (isStrikePoint(this.point.getPoint() + point)) {
            return new Spare(point);
        }
        return getMissOrGutterScore(point);
    }
}
