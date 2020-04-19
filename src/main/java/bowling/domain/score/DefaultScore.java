package bowling.domain.score;

public class DefaultScore extends Score {

    public DefaultScore(ScoreType scoreType, int point) {
        super(scoreType, point);
    }

    public static DefaultScore of(int point) {
        if (isStrikePoint(point)) {
            return new DefaultScore(ScoreType.STRIKE, point);
        }
        return new DefaultScore(getMissOrGutterType(point), point);
    }

    @Override
    public Score nextScore(int point) {
        if (isStrikePoint(getPoint() + point)) {
            return new NomalScore(ScoreType.SPARE, point);
        }
        return new NomalScore(getMissOrGutterType(point), point);
    }
}
