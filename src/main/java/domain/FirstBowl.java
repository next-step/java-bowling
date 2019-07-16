package domain;

import static domain.Frame.FIRST_IS_NOT_SPARE;
import static domain.Frame.ZERO;

public class FirstBowl extends State {
    private final String STATE_NAME = "FirstBowl";

    private Pin firstPin;

    FirstBowl(int firstPins) {
        firstPin = Pin.of(firstPins);
    }

    @Override
    public State bowl(int secondPins) {
        Pin secondPin = Pin.of(secondPins);

        if (FrameCounter.isFinalFrame()) {
            return new FinalBowl(firstPin, secondPin);
        }

        if (firstPin.isSpare(secondPin)) {
            return new Spare(firstPin, secondPin);
        }

        return new Miss(firstPin, secondPin);
    }

    @Override
    public int getPoints() {
        return firstPin.getFellPins();
    }

    @Override
    Pin getFirstPin() {
        return firstPin;
    }

    @Override
    Pin getSecondPin() {
        return null;
    }

    @Override
    boolean isFrameEnd() {
        return Boolean.FALSE;
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
    public String getPoint() {
        int firstPins = firstPin.getFellPins();
        String result = PointName.valueOfPointName(firstPins, FIRST_IS_NOT_SPARE);
        return String.format("%-4s", result);
    }

    @Override
    public Score getScore() {
        return new Score(firstPin.getFellPins(), ZERO);
    }
}
