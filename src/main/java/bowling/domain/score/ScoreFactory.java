package bowling.domain.score;

public class ScoreFactory {
    private static final int STRIKE_SCORE = 10;
    private static final int GUTTER_SCORE = 0;

    public static Score generateScore(int point) {
        switch (point) {
            case STRIKE_SCORE:
                return new Strike();
            case GUTTER_SCORE:
                return new Gutter();
            default:
                return new Normal(point);
        }
    }
}
