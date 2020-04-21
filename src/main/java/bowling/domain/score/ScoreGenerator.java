package bowling.domain.score;

public class ScoreGenerator {
    private static final int STRIKE_POINT = 10;

    public static Score nextScore(int point) {
        if (isStrikePoint(point)) {
            return new Strike(point);
        }
        return NomalScore.of(point);
    }

    private static boolean isStrikePoint(int point) {
        return point == STRIKE_POINT;
    }
}
