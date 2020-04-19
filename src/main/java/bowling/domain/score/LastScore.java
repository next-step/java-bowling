package bowling.domain.score;

public class LastScore extends Score {

    public LastScore(ScoreType scoreType, int point) {
        super(scoreType, point);
    }

    public static LastScore of(int point) {
        if (isStrikePoint(point)) {
            return new LastScore(ScoreType.STRIKE, point);
        }
        return new LastScore(getMissOrGutterType(point), point);
    }

    @Override
    public Score nextScore(int point) {
        if (isGutterPoint(point)) {
            return new LastScoreSecond(ScoreType.GUTTER, point);
        }
        if (isStrikePoint(getPoint()) && isStrikePoint(point)) {
            return new LastScoreSecond(ScoreType.STRIKE, point);
        }
        if (isStrikePoint(getPoint() + point)) {
            return new LastScoreSecond(ScoreType.SPARE, point);
        }
        return new LastScoreSecond(ScoreType.MISS, point);
    }
}
