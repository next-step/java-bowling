package bowling.domain;

import java.util.Objects;

public class NormalFrame implements Frame {

    private int index;

    private Points points;

    private static final int START_INDEX = 1;
    public static final int MAX_TRY = 2;


    private NormalFrame() {
        this.index = START_INDEX;
        this.points = new Points();
    }

    public NormalFrame(int index) {
        this.index = index;
        this.points = new Points();
    }

    public static NormalFrame first() {
        return new NormalFrame();
    }

    @Override
    public void bowl(int hitPin) {
        int currentPoint = points.currentPointSum();

        if (currentPoint + hitPin > Point.MAX_POINT) {
            throw new IllegalArgumentException("합계 점수가 10이 넘을 수 없습니다");
        }
        points.addPoint(new Point(hitPin));
    }

    @Override
    public boolean isFinished() {
        if (points.bowlCount() == Point.MIN_POINT) {
            return false;
        }
        if (points.bowlCount() == MAX_TRY) {
            return true;
        }
        if (points.findFirstPointScore() == Point.MAX_POINT) {
            return true;
        }
        return false;
    }

    @Override
    public int findCurrentIndex() {
        return index;
    }

    @Override
    public NormalFrame next() {
        return new NormalFrame(this.index + 1);
    }

    @Override
    public Points currentFramePoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return index == that.index && Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, points);
    }
}
