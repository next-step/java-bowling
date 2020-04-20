package bowling.domain.score;

public class EmptyScore{
    private static final int STRIKE_POINT = 10;

    public static Score nextScore(int point) {
        if (isStrikePoint(point)) {
            return new Strike(point);
        }
        return new NomalScore(point);
    }

    private static boolean isStrikePoint(int point) {
        return point == STRIKE_POINT;
    }
}
