package bowling.domain.score;

public class Strike extends Score {
    public Strike(int point) {
        super(point, ScoreType.STRIKE);
    }

    @Override
    public Score nextScore(int point) {
        if (isStrikePoint(point)) {
            return new Strike(point);
        }
        return NomalScore.of(point);
    }
}
