package bowling.domain.state;

import bowling.exception.IllegalPointException;

public class Spare implements PitchState {
    private int firstPoint;
    private int secondPoint;

    public Spare(int firstPoint, int secondPoint) {
        pointValidate(firstPoint, secondPoint);

        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    private void pointValidate(int firstPoint, int secondPoint) {
        if (firstPoint + secondPoint != Strike.STRIKE) {
            throw new IllegalPointException("잘못된 point 값입니다");
        }
    }

    @Override
    public PitchState next(int point) {
        return new Spare(firstPoint, secondPoint);
    }

    @Override
    public boolean isNext() {
        return false;
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public int getFirstPoint() {
        return firstPoint;
    }

    @Override
    public int getSecondPoint() {
        return secondPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spare)) return false;

        Spare spare = (Spare) o;

        if (firstPoint != spare.firstPoint) return false;
        return secondPoint == spare.secondPoint;
    }

    @Override
    public int hashCode() {
        int result = firstPoint;
        result = 31 * result + secondPoint;
        return result;
    }
}
