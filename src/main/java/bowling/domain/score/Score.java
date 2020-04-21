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
    final ScoreType scoreType;

    public Score(int point, ScoreType scoreType) {
        this.point = Point.valueOf(point);
        this.scoreType = scoreType;
    }

    public abstract Score nextScore(int point);

    public boolean isEqualScoreType(ScoreType scoreType) {
        return this.scoreType.equals(scoreType);
    }

    public int getPoint() {
        return point.getPoint();
    };

    public String getScore() {
        return scoreType.pointToScore(point.getPoint());
    }

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
