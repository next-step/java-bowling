package bowling.domain.score;

public class Strike extends Score {
    public Strike(int point) {
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
        return scoreType.equals(ScoreType.STRIKE);
    }

    @Override
    public String getScore() {
        return ScoreType.STRIKE.pointToScore(point.getPoint());
    }
}
