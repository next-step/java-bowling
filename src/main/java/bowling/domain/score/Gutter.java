package bowling.domain.score;

public class Gutter extends Score {
    public Gutter(int point) {
        super(point, ScoreType.GUTTER);
    }

    @Override
    public Score nextScore(int point) {
        if (isStrikePoint(this.point.getPoint() + point)) {
            return new Spare(point);
        }
        return getMissOrGutterScore(point);
    }
}
