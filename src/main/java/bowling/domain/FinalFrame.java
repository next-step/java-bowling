package bowling.domain;

import java.util.Objects;

public class FinalFrame {

    private int index;
    private Points points;

    private static final int FINAL_INDEX = 10;
    private static final int MAX_TRY = 3;


    public FinalFrame() {
        this.index = FINAL_INDEX;
        this.points = new Points();
    }

    public void bowl(int hitPin) {
        int currentPoint = points.currentPoint();

        if (currentPoint + hitPin > Point.FINAL_MAX_POINT) {
            throw new IllegalArgumentException("합계 점수가 20이 넘을 수 없습니다");
        }
        points.addPoint(new Point(hitPin));
    }

    public boolean isFinished() {

        if (points.bowlCount() == MAX_TRY) {
            return true;
        }

        if (!points.canPlayBonusGame()) {
            return true;
        }

        return false;
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
