package domain;

import static domain.Pin.MAX_PINS;

public class FinalBowl extends State {
    private final String STATE_NAME = "FinalBowl";

    private int firstPin;
    private int secondPin;
    private int bonusPin;

    public FinalBowl(int firstPin, int secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
        this.bonusPin = -1;
    }

    @Override
    public State bowl(int countOfPins) {
        if ((firstPin + secondPin) < MAX_PINS) {
            throw new IllegalStateException("2번 투구까지 커버되지 않으면 보너스 투구할 수 없습니다.");
        }
        bonusPin = countOfPins;
        return this;
    }

    @Override
    public int getFellPins() {
        return firstPin + secondPin + bonusPin;
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
        Pin first = Pin.of(firstPin);
        Pin second = Pin.of(secondPin);
        boolean isBonus = first.isSpare(second) || first.isStrike();
        return (isBonus && bonusPin == -1) ? Boolean.TRUE : Boolean.FALSE;
    }
}
