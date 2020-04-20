package bowling.domain.score;

public class Spare extends Score {
    public Spare(int point) {
        super(point);
    }

    @Override
    public Score nextScore(int point) {
        if (isStrikePoint(point)) {
            return new Strike(point);
        }
        return new NomalScore(point);
    }

    @Override
    public boolean isEqualScoreType(ScoreType scoreType) {
        return scoreType.equals(ScoreType.SPARE);
    }

    @Override
    public String getScore() {
        return ScoreType.SPARE.pointToScore(point.getPoint());
    }
}
