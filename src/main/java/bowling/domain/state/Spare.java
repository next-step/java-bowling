package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

public class Spare implements State {

    private static final String INVALID_END_PLAY = "더이상 진행할 수 없습니다.";
    private static final String INVALID_PIN_COUNT = "잘못된 투구입니다.";
    private final Pin firstPin;
    private final Pin secondPin;

    public Spare(int firstPin, int secondPin) {
        this(new Pin(firstPin), new Pin(secondPin));
    }

    public Spare(Pin firstPin, Pin secondPin) {
        validate(firstPin, secondPin);
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    private void validate(Pin firstPin, Pin secondPin) {
        if (!firstPin.validate(secondPin) || !firstPin.isSpare(secondPin)) {
            throw new IllegalArgumentException(INVALID_PIN_COUNT);
        }
    }

    @Override
    public int getPitchCount() {
        return 2;
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
    }

    @Override
    public int getTotalCount() {
        return 10;
    }

    @Override
    public String toString() {
        return firstPin.toString() + "|/";
    }

    @Override
    public State play(Pin fallenPin) {
        throw new IllegalArgumentException(INVALID_END_PLAY);
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
