package bowling.domain.score;

import bowling.domain.point.Point;

/**
 * 점수를 나타내는 객체 쓰러트린 볼링핀 개수와 점수 타입을 가진다.
 * 10점을 넘을 수 없다.
 */
public class Score {
    private final ScoreType scoreType;
    private final Point point;

    public Score(ScoreType scoreType, int point) {
        this.scoreType = scoreType;
        this.point = new Point(point);
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
