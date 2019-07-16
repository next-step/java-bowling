package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static domain.Frame.*;
import static domain.Pin.MAX_PINS;

public class FinalBowl extends State {
    private final String STATE_NAME = "FinalBowl";

    private List<Pin> pins;

    public FinalBowl(Pin firstPin, Pin secondPin) {
        pins = new ArrayList<>();
        pins.add(firstPin);
        pins.add(secondPin);
    }

    @Override
    public State bowl(int countOfPins) {
        if (getPoints() < MAX_PINS) {
            throw new IllegalStateException("2번 투구까지 커버되지 않으면 보너스 투구할 수 없습니다.");
        }
        pins.add(Pin.of(countOfPins));
        return this;
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
        Optional<Pin> maybeThird = Optional.empty();
        if (pins.size() == THIRD + 1) {
            maybeThird = Optional.ofNullable(pins.get(THIRD));
        }
        return canBonus() && !maybeThird.isPresent() ? Boolean.FALSE : Boolean.TRUE;
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
        Optional<Pin> maybeThirdPin = Optional.empty();
        if (pins.size() == THIRD + 1) {
            maybeThirdPin = Optional.ofNullable(pins.get(THIRD));
        }

        Pin firstPin = pins.get(FIRST);
        Pin secondPin = pins.get(SECOND);
        Pin thirdPin = maybeThirdPin.orElse(Pin.of(ZERO));

        int firstPins = firstPin.getFellPins();
        int secondPins = secondPin.getFellPins();
        int thirdPins = maybeThirdPin.isPresent() ? thirdPin.getFellPins() : NO_MORE_NEXT;

        String first = PointName.valueOfPointName(firstPins, FIRST_IS_NOT_SPARE);
        String second = PointName.valueOfPointName(secondPins, firstPin.isSpare(secondPin));
        String third = PointName.valueOfPointName(thirdPins, secondPin.isSpare(thirdPin));

        String pointResult = first + addConnector(second) + addConnector(third);
        return String.format("%-4s", pointResult);
    }

    @Override
    public Score getScore() {
        final int TOTAL_LEFT = 3 + (canBonus() ? ZERO : NO_MORE_NEXT);

        int left = TOTAL_LEFT % pins.size();
        return new Score(getPoints(), left);
    }

    private String addConnector(String text) {
        final String CONNECTOR = "|";

        if (!text.equals("")) {
            return CONNECTOR + text;
        }
        return text;
    }

    private boolean canBonus() {
        Pin first = pins.get(FIRST);
        Pin second = pins.get(SECOND);
        return first.isStrike() || first.isSpare(second);
    }
}
