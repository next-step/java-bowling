package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

public class Spare implements State {

    public static final int SPARE_PITCH = 2;
    public static final String VALID_PIN = "핀은 총 10개여야합니다.";
    private int firstBowl;
    private int secondBowl;

    public Spare(int firstBowl, int secondBowl) {
        if (firstBowl + secondBowl != Pin.MAX_PIN_COUNT) {
            throw new IllegalArgumentException(VALID_PIN);
        }

        this.firstBowl = firstBowl;
        this.secondBowl = secondBowl;
    }

    @Override
    public State bowl(int countOfPins) {
        throw new IllegalArgumentException(Miss.INVALID_PLAY);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public int getPitchCount() {
        return SPARE_PITCH;
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
    }

    @Override
    public int getTotalCount() {
        return Pin.MAX_PIN_COUNT;
    }

    @Override
    public String toString() {
        return firstBowl + "|" + "/";
    }
}
