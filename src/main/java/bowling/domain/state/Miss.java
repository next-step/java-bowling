package bowling.domain.state;

import bowling.exception.IllegalPointException;

public class Miss implements PitchState {
    private int firstPoint;
    private int secondPoint;

    public Miss(int firstPoint, int secondPoint) {
        pointValidate(firstPoint, secondPoint);

        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    private void pointValidate(int firstPoint, int secondPoint) {
        if (firstPoint + secondPoint > Strike.STRIKE - 1) {
            throw new IllegalPointException("잘못된 point 값입니다");
        }
    }

    @Override
    public PitchState next(int point) {
        return new Miss(firstPoint, secondPoint);
    }

    @Override
    public boolean isNext() {
        return false;
    }

    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public int getFirstPoint() {
        return firstPoint;
    }

    @Override
    public int getSecondPoint() {
        return secondPoint;
    }
}
