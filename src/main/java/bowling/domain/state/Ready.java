package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

public class Ready implements State {

    public static final int READY_PITCH = 0;
    public static final String NOT_FINISH = "프레임이 종료되지 않았습니다.";

    @Override
    public State bowl(int countOfPins) {
        if (countOfPins == 10) {
            return new Strike();
        }

        return new FirstBowl(countOfPins);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public int getPitchCount() {
        return READY_PITCH;
    }

    @Override
    public Score getScore() {
        throw new IllegalStateException(NOT_FINISH);
    }

    @Override
    public int getTotalCount() {
        return Pin.MIN_PIN_COUNT;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
