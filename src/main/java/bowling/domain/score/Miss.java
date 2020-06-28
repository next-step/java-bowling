package bowling.domain.score;

import bowling.domain.point.Point;

public class Miss implements Score {

    private static final ScoreType SCORE_TYPE = ScoreType.MISS;

    private final Point point;

    public Miss(Point point) {
        this.point = point;
    }

    @Override
    public Score nextScore(Point point) {
        throw new IllegalArgumentException("MISS 다음 점수를 생성할 수 없습니다.");
    }

    @Override
    public ScoreType getScore() {
        return SCORE_TYPE;
    }

    @Override
    public Point getPoint() {
        return point;
    }
}
