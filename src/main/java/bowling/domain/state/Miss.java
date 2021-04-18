package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

public class Miss implements State {
    private final Pin firstPin;
    private final Pin secondPin;
    private static final String INVALID_END_PLAY = "더이상 진행할 수 없습니다.";
    private static final String INVALID_PIN_COUNT = "잘못된 투구입니다.";

    public Miss(int firstPin, int secondPin) {
        this(new Pin(firstPin), new Pin(secondPin));
    }

    public Miss(Pin firstPin, Pin secondPin) {
        validate(firstPin, secondPin);
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    private void validate(Pin firstPin, Pin secondPin) {
        if (firstPin.getCount() + secondPin.getCount() >= 10) {
            throw new IllegalArgumentException(INVALID_PIN_COUNT);
        }
    }

    @Override
    public int getPitchCount() {
        return 2;
    }

    @Override
    public Score getScore() {
        return Score.ofMiss(firstPin.sum(secondPin));
    }

    @Override
    public int getTotalCount() {
        return firstPin.sum(secondPin);
    }

    @Override
    public String toString() {
        return firstPin.toString() + "|" + secondPin.toString();
    }

    @Override
    public State play(int fallenPin) {
        throw new IllegalArgumentException(INVALID_END_PLAY);
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
