package domain;

import static domain.Pin.MAX_PINS;

public class Miss extends State {
    private final String STATE_NAME = "Miss";

    private int firstPin;
    private int secondPin;

    Miss(int firstPin, int secondPin) {
        if(firstPin + secondPin > MAX_PINS) {
            throw new IllegalArgumentException("두 번의 투구 합은 10을 초과할 수 없습니다.");
        }
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    State bowl(int countOfPins) {
        return null;
    }

    @Override
    public int getFellPins() {
        return firstPin + secondPin;
    }

    @Override
    public int getFirstPin() {
        return firstPin;
    }

    @Override
    public int getSecondPin() {
        return secondPin;
    }

    @Override
    public boolean isNameOfState(String state) {
        return STATE_NAME.equals(state);
    }

    @Override
    public String getNameOfState() {
        return STATE_NAME;
    }

    @Override
    boolean nowPlaying() {
        return Boolean.TRUE;
    }
}
