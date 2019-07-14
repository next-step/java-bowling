package domain;

import java.util.ArrayList;
import java.util.List;

import static domain.Frame.FIRST_IS_NOT_SPARE;
import static domain.Pin.MAX_PINS;

public class Miss extends State {
    private final String STATE_NAME = "Miss";

    private List<Pin> pins;

    Miss(Pin firstPin, Pin secondPin) {
        if (firstPin.getFellPins() + secondPin.getFellPins() > MAX_PINS) {
            throw new IllegalArgumentException("두 번의 투구 합은 10을 초과할 수 없습니다.");
        }
        pins = new ArrayList<>();
        pins.add(firstPin);
        pins.add(secondPin);
    }

    @Override
    State bowl(int countOfPins) {
        return null;
    }

    @Override
    public int getPoints() {
        return pins.stream()
                .mapToInt(pin -> pin.getFellPins())
                .sum();
    }

    @Override
    public Pin getFirstPin() {
        return pins.get(FIRST);
    }

    @Override
    public Pin getSecondPin() {
        return pins.get(SECOND);
    }

    @Override
    boolean isFrameEnd() {
        return Boolean.TRUE;
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
        Pin firstPin = pins.get(FIRST);
        Pin secondPin = pins.get(SECOND);

        int firstPins = firstPin.getFellPins();
        int secondPins = secondPin.getFellPins();

        String first = PointName.valueOfPointName(firstPins, FIRST_IS_NOT_SPARE);
        String second = PointName.valueOfPointName(secondPins, firstPin.isSpare(secondPin));
        String pointResult = first + "|" + second;

        return String.format("%-4s", pointResult);
    }

    @Override
    public Score getScore() {
        return Score.ofMiss(getPoints());
    }
}
