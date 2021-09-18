package bowling.domain;

import java.util.Objects;

public class FinalFrame implements Frame {

    private int index;
    private Points points;
    public static final int MAX_TRY = 3;


    public FinalFrame() {
        this.index = Frames.FINAL_INDEX;
        this.points = new Points();
    }

    @Override
    public void bowl(int hitPin) {
        int currentPoint = points.currentPointSum();

        if (currentPoint + hitPin > Point.FINAL_MAX_POINT) {
            throw new IllegalArgumentException("합계 점수가 20이 넘을 수 없습니다");
        }
        makePoint(hitPin);
    }

    public void makePoint(int hitPin) {
        if (points.bowlCount() == MAX_TRY - 1) {
            points.addBonusPoint(hitPin);
            return;
        }
        if (points.currentPointSum() == Point.MAX_POINT && points.bowlCount() == MAX_TRY - 2) {
            points.addBonusPoint(hitPin);
            return;
        }
        points.addPoint(hitPin);
    }

    @Override
    public boolean isFinished() {
        if (points.bowlCount() == Point.MIN_POINT) {
            return false;
        }
        if (points.bowlCount() == Point.MAX_POINT) {
            return false;
        }
        if (points.bowlCount() == MAX_TRY) {
            return true;
        }
        if (hasNotBonusGame()) {
            return true;
        }
        return false;
    }

    private boolean hasNotBonusGame() {
        if ((points.findFirstPointScore() == Point.MAX_POINT && points.bowlCount() == MAX_TRY - 1)
                ||
                (points.values()
                        .stream()
                        .mapToInt(Point::currentPoint)
                        .sum() == Point.MAX_POINT && points.bowlCount() == MAX_TRY)) {
            return true;
        }
        return false;
    }

    @Override
    public Points currentFramePoints() {
        return points;
    }

    @Override
    public int findCurrentIndex() {
        return index;
    }

    @Override
    public Frame next() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return index == that.index && Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, points);
    }
}
