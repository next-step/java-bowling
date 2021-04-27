package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

public class Strike implements State {
    public static final String NOT_PITCH_BOWL = "프레임이 종료되었습니다.";
    public static final int STRIKE_PITCH = 1;

    @Override
    public State bowl(int countOfPins) {
        throw new IllegalArgumentException(NOT_PITCH_BOWL);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public int getPitchCount() {
        return STRIKE_PITCH;
    }

    @Override
    public Score getScore() {
        return Score.ofStrike();
    }

    @Override
    public int getTotalCount() {
        return Pin.MAX_PIN_COUNT;
    }

    @Override
    public String toString() {
        return "X";
    }
}
