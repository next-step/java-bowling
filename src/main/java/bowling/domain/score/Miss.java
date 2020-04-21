package bowling.domain.score;

public class Miss extends Score {
    public Miss(int point) {
        super(point, ScoreType.MISS);
    }

    @Override
    public Score nextScore(int point) {
        if (isStrikePoint(this.point.getPoint() + point)) {
            return new Spare(point);
        }
        return getMissOrGutterScore(point);
    }
}
