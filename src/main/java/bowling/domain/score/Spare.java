package bowling.domain.score;

public class Spare extends Score {
    public Spare(int point) {
        super(point, ScoreType.SPARE);
    }

    @Override
    public Score nextScore(int point) {
        if (isStrikePoint(point)) {
            return new Strike(point);
        }
        return NomalScore.of(point);
    }
}
