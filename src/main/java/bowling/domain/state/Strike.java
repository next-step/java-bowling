package bowling.domain.state;

import bowling.exception.IllegalPointException;

public class Strike implements PitchState {
    public static final int STRIKE = 10;
    public int point;

    public Strike(int point) {
        pointValidate(point);
        this.point = point;
    }

    private void pointValidate(int point) {
        if (point != STRIKE) {
            throw new IllegalPointException("잘못된 point입니다.");
        }
    }

    @Override
    public PitchState next(int point) {
        return new Strike(point);
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
        return Strike.STRIKE;
    }

    public int getSecondPoint() {
        return -1;
    }
}
