package domain;

import java.util.ArrayList;
import java.util.List;

import static domain.Frame.FIRST_IS_NOT_SPARE;

public class Strike extends State {
    private final String STATE_NAME = "Strike";
    private final int STRIKE = 10;

    private List<Pin> pins;

    Strike() {
        pins = new ArrayList<>();
        pins.add(Pin.of(STRIKE));
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
    Pin getFirstPin() {
        return pins.get(FIRST);
    }

    @Override
    Pin getSecondPin() {
        return null;
    }

    @Override
    boolean isFrameEnd() {
        return Boolean.TRUE;
    }

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
        int firstPins = firstPin.getFellPins();
        String result = PointName.valueOfPointName(firstPins, FIRST_IS_NOT_SPARE);

        return String.format("%-4s", result);
    }

    @Override
    public Score getScore() {
        return Score.ofStrike();
    }
}
