package bowling.domain.score;

public class LastScoreSecond extends Score {

    public LastScoreSecond(ScoreType scoreType, int point) {
        super(scoreType, point);
    }

    @Override
    public Score nextScore(int point) {
        if (isStrikeOrSpare() && isStrikePoint(point)) {
            return new NomalScore(ScoreType.STRIKE, point);
        }
        if (isStrikePoint(getPoint() + point)) {
            return new NomalScore(ScoreType.SPARE, point);
        }
        return new NomalScore(getMissOrGutterType(point), point);
    }

    private boolean isStrikeOrSpare() {
        return isEqualScoreType(ScoreType.STRIKE) || isEqualScoreType(ScoreType.SPARE);
    }
}
