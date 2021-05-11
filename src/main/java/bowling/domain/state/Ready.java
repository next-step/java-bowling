package bowling.domain.state;

import bowling.exception.IllegalPointException;

public class Ready implements PitchState {
    private int point;

    public Ready(int point) {
        pointValidate(point);

        this.point = point;
    }

    private void pointValidate(int point) {
        if (point < Start.MIN_POINT || point > Strike.STRIKE) {
            throw new IllegalPointException("잘못된 포인트 값입니다.");
        }

        if (this.point + point > 10) {
            throw new IllegalPointException("잘못된 포인트 값입니다.");
        }
    }

    public PitchState next(int point) {
        if (this.point + point == Strike.STRIKE) {
            return new Spare(this.point, point);
        }

        return new Miss(this.point, point);
    }

    @Override
    public boolean isNext() {
        return true;
    }

    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public int getFirstPoint() {
        return point;
    }

    @Override
    public int getSecondPoint() {
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ready)) return false;

        Ready ready = (Ready) o;

        return point == ready.point;
    }

    @Override
    public int hashCode() {
        return point;
    }
}
