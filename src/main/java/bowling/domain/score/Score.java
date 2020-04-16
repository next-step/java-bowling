package bowling.domain.score;

import bowling.domain.Point;

/**
 * 점수를 나타내는 객체 쓰러트린 볼링핀 개수와 점수 타입을 가진다.
 * 10점을 넘을 수 없다.
 */
public class Score {
    private static final int STRIKE_POINT = 10;
    private static final int ZERO_POINT = 0;
    private static final int FIRST_PLAY = 0;
    private static final int SECOND_PLAY = 1;

    private final ScoreType scoreType;
    private final Point point;

    private Score(ScoreType scoreType, Point point) {
        this.scoreType = scoreType;
        this.point = point;
    }

    public static Score defaultFrameScore(Scores scores, int point) {
        return new Score(generateDefaultScoreType(scores, point), new Point(point));
    }

    private static ScoreType generateDefaultScoreType(Scores scores, int point) {
        if (scores.size() == FIRST_PLAY && point == STRIKE_POINT) {
            return ScoreType.STRIKE;
        }
        return getSpareOrGutterType(scores, point);
    }

    public static Score lastFrameScore(Scores scores, int point) {
        return new Score(generateLastScoreType(scores, point), new Point(point));
    }

    private static ScoreType generateLastScoreType(Scores scores, int point) {
        if (point == STRIKE_POINT) {
            return ScoreType.STRIKE;
        }
        return getSpareOrGutterType(scores, point);
    }

    private static ScoreType getSpareOrGutterType(Scores scores, int point) {
        if (scores.size() == SECOND_PLAY && scores.currentPoint() + point == STRIKE_POINT) {
            return ScoreType.SPARE;
        }
        if (point == ZERO_POINT) {
            return ScoreType.GUTTER;
        }
        return ScoreType.MISS;
    }

    public int getPoint() {
        return point.getPoint();
    }

    public boolean isEqualScoreType(ScoreType scoreType) {
        return this.scoreType.equals(scoreType);
    }

    public String pointToScore() {
        return scoreType.pointToScore(point.getPoint());
    }
}
