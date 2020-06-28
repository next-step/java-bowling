package bowling.domain.score;

import bowling.domain.point.Point;

public class Miss implements Score {

    private final Point point;

    public Miss(Point point) {
        this.point = point;
    }

    @Override
    public Score nextScore(Point point) {
        throw new IllegalArgumentException("MISS 다음 점수를 생성할 수 없습니다.");
    }

    @Override
    public String getScore() {
        return point.toString();
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }
}
