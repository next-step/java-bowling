package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

public class Miss implements State {

    public static final int MISS_PITCH = 2;
    public static final String VALID_PIN = "핀의 개수는 10개를 넘을 수 없습니다.";
    public static final String INVALID_PLAY = "진행할 수 없습니다.";

    private int firstBowl;
    private int secondBowl;

    public Miss(int firstBowl, int secondBowl) {
        if (firstBowl + secondBowl > Pin.MAX_PIN_COUNT) {
            throw new IllegalArgumentException(VALID_PIN);
        }
        this.firstBowl = firstBowl;
        this.secondBowl = secondBowl;
    }

    @Override
    public State bowl(int countOfPins) {
        throw new IllegalArgumentException(INVALID_PLAY);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public int getPitchCount() {
        return MISS_PITCH;
    }

    @Override
    public Score getScore() {
        return Score.ofMiss(firstBowl + secondBowl);
    }

    @Override
    public int getTotalCount() {
        return firstBowl + secondBowl;
    }

    @Override
    public String toString() {
        if (firstBowl == 0 && secondBowl != 0) {
            return "-" + "|" +secondBowl;
        }

        if (firstBowl != 0 && secondBowl == 0) {
            return firstBowl + "|" + "-";
        }

        return firstBowl + "|" + secondBowl;
    }

}
