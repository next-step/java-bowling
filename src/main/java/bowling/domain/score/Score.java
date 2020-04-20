package bowling.domain.score;

import bowling.domain.point.Point;

/**
 * 점수를 나타내는 객체 쓰러트린 볼링핀 개수를 가진다.
 * 10점을 넘을 수 없다.
 */
public abstract class Score {
    private static final int STRIKE_POINT = 10;
    private static final int GUTTER_POINT = 0;

    final Point point;

    public Score(int point) {
        this.point = Point.valueOf(point);
    }

    public abstract Score nextScore(int point);

    public abstract boolean isEqualScoreType(ScoreType scoreType);

    public int getPoint() {
        return point.getPoint();
    };

    public abstract String getScore();

    static boolean isStrikePoint(int point) {
        return point == STRIKE_POINT;
    }

    static boolean isGutterPoint(int point) {
        return point == GUTTER_POINT;
    }

    static Score getMissOrGutterScore(int point) {
        if (isGutterPoint(point)) {
            return new Gutter(point);
        }
        return new Miss(point);
    }
}
