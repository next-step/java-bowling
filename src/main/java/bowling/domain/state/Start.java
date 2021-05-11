package bowling.domain.state;

import bowling.exception.IllegalPointException;

public class Start implements PitchState {
    public static final int MIN_POINT = 0;
    public static final Start start = new Start();

    private Start() {
    }

    public static Start createOf() {
        return start;
    }

    @Override
    public PitchState next(int point) {
        pointValidate(point);

        if (point == Strike.STRIKE) {
            return new Strike(point);
        }

        return new Ready(point);
    }

    private void pointValidate(int point) {
        if (point < MIN_POINT || point > Strike.STRIKE) {
            throw new IllegalPointException("잘못된 포인트 값입니다.");
        }
    }

    @Override
    public boolean isNext() {
        return true;
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public int getFirstPoint() {
        return MIN_POINT;
    }

    public int getSecondPoint() {
        return -1;
    }

}
