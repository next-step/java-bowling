package bowling.domain.score;

import bowling.domain.point.Point;

public class Spare implements Score {

    private final Point point;

    public Spare(Point point) {
        this.point = point;
    }

    @Override
    public Score nextScore(Point point) {
        throw new IllegalArgumentException("스페어 처리 이후 다음 점수를 입력할 수 없습니다.");
    }

    @Override
    public String getScore() {
        return "/";
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return true;
    }
}
