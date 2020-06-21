package bowling.domain.score;

public class Gutter implements Score {
    private static final int POINT = 0;
    private static final ScoreType SCORE_TYPE = ScoreType.GUTTER;

    @Override
    public Score nextScore(int point) {
        if (point > 10) {
            throw new IllegalArgumentException("total Score less than 10");
        }

        if (point == 10) {
            return new Spare(point);
        }

        if (point == 0) {
            return new Gutter();
        }

        return new Normal(point);
    }

    @Override
    public int getPoint() {
        return POINT;
    }

    @Override
    public ScoreType getScoreType() {
        return SCORE_TYPE;
    }
}
