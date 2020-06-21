package bowling.domain.score;

public class ScoreFactory {
    private static final int STRIKE_SCORE = 10;
    private static final int GUTTER_SCORE = 0;

    public static Score generateScore(int point) {
        validatePoint(point);
        switch (point) {
            case STRIKE_SCORE:
                return new Strike();
            case GUTTER_SCORE:
                return new Gutter();
            default:
                return new Normal(point);
        }
    }

    private static void validatePoint(int point) {
        if (point < GUTTER_SCORE || point > STRIKE_SCORE) {
            throw new IllegalArgumentException("not valid point");
        }
    }
}
