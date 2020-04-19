package bowling.domain.score;

import bowling.domain.point.Point;

/**
 * 점수를 나타내는 객체 쓰러트린 볼링핀 개수와 점수 타입을 가진다.
 * 10점을 넘을 수 없다.
 */
public abstract class Score {
    private static final int STRIKE_POINT = 10;
    private static final int GUTTER_POINT = 0;

    private final ScoreType scoreType;
    private final Point point;

    protected Score(ScoreType scoreType, int point) {
        this.scoreType = scoreType;
        this.point = Point.valueOf(point);
    }

    public abstract Score nextScore(int point);

    public int getPoint() {
        return point.getPoint();
    }

    public boolean isEqualScoreType(ScoreType scoreType) {
        return this.scoreType.equals(scoreType);
    }

    public String pointToScore() {
        return scoreType.pointToScore(point.getPoint());
    }

    protected static boolean isStrikePoint(int point) {
        return point == STRIKE_POINT;
    }

    protected static boolean isGutterPoint(int point) {
        return point == GUTTER_POINT;
    }

    protected static ScoreType getMissOrGutterType(int point) {
        if (isGutterPoint(point)) {
            return ScoreType.GUTTER;
        }
        return ScoreType.MISS;
    }
}
