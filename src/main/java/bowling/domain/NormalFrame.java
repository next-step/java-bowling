package bowling.domain;

import java.util.Objects;

public class NormalFrame {

    private int index;

    private Points points;

    private static final int START_INDEX = 0;
    private static final int NEXT_INDEX_DISTANCE = 1;
    private static final int MAX_TRY = 2;


    private NormalFrame() {
        this.index = START_INDEX;
        this.points = new Points();
    }

    public NormalFrame(int index) {
        this.index = index;
    }

    public static NormalFrame first() {
        return new NormalFrame();
    }

    public void bowl(int hitPin) {
        int currentPoint = points.currentPoint();

        if (currentPoint + hitPin > Point.MAX_POINT) {
            throw new IllegalArgumentException("합계 점수가 10이 넘을 수 없습니다");
        }
        points.addPoint(new Point(hitPin));
    }

    public boolean isFinished() {
        if (points.bowlCount() == MAX_TRY) {
            return true;
        }
        if (points.findFirstPoint() == 10) {
            return true;
        }
        return false;
    }

    public NormalFrame next() {
        return new NormalFrame(this.index + NEXT_INDEX_DISTANCE);
    }

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
