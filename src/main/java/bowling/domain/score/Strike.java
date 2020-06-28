package bowling.domain.score;

import bowling.domain.point.Point;

public class Strike implements Score {

    private static final ScoreType SCORE_TYPE = ScoreType.STRIKE;

    private static final Point point = Point.inputPoint(STRIKE_POINT);

    @Override
    public Score nextScore(Point point) {
        throw new IllegalArgumentException("스트라이크 다음에는 점수를 입력할 수 없습니다.");
    }

    @Override
    public ScoreType getScore() {
        return SCORE_TYPE;
    }

    @Override
    public Point getPoint() {
        return point;
    }

    @Override
    public boolean isStrike() {
        return true;
    }

    @Override
    public boolean isSpare() {
        return false;
    }
}
