package bowling.domain;

import java.util.Objects;

public class FinalFrame implements Frame {

    private int index;
    private Points points;

    private static final int FINAL_INDEX = 10;
    private static final int MAX_TRY = 3;


    public FinalFrame() {
        this.index = FINAL_INDEX;
        this.points = new Points();
    }

    @Override
    public void bowl(int hitPin) {
        int currentPoint = points.currentPoint();

        if (currentPoint + hitPin > Point.FINAL_MAX_POINT) {
            throw new IllegalArgumentException("합계 점수가 20이 넘을 수 없습니다");
        }
        points.addPoint(new Point(hitPin));
    }

    @Override
    public boolean isFinished() {
        if (points.bowlCount() == 0) {
            return false;
        }
        if (points.bowlCount() == MAX_TRY) {
            return true;
        }
        if (points.findFirstPoint() == 10 && points.bowlCount() == 2) {
            return true;
        }
        if (points.values().stream()
                .mapToInt(Point::currentPoint)
                .sum() == 10 && points.bowlCount() == 3) {
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
    public void addPoint(Point point) {
        points.addPoint(point);
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
