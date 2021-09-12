package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NormalFrame {

    private int index;

    private List<Point> points = new ArrayList<>();

    private static final int START_INDEX = 0;
    private static final int NEXT_INDEX_DISTANCE = 1;


    private NormalFrame() {
        this.index = START_INDEX;
    }

    public NormalFrame(int index) {
        this.index = index;
    }

    public static NormalFrame first() {
        return new NormalFrame();
    }

    public void bowl(int hitPin) {
        int currentPoint = points.stream()
                .mapToInt(Point::currentPoint)
                .sum();
        if (currentPoint + hitPin > Point.MAX_POINT) {
            throw new IllegalArgumentException("합계 점수가 10이 넘을 수 없습니다");
        }
        points.add(new Point(hitPin));
    }

    public NormalFrame next() {
        return new NormalFrame(this.index + NEXT_INDEX_DISTANCE);
    }

    public List<Point> currentFramePoints() {
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
