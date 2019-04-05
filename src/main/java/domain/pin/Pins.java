package domain.pin;

import domain.status.Status;

import java.util.ArrayList;
import java.util.List;

import static domain.pin.Pin.MAXIMUM_PINS;

public class Pins {
    private final List<Pin> pins = new ArrayList<>();

    public List<Pin> getPins() {
        return pins;
    }

    public int getSize() {
        return pins.size();
    }

    public Status getRecentStatus() {
        return pins.get(pins.size()-1).getStatus();
    }

    public void add(Pin pin) {
        if(isInvalidPins(pin)) {
            throw new IllegalArgumentException("핀의 개수는 " + MAXIMUM_PINS + "개를 초과할 수 없습니다.");
        }

        pins.add(pin);
    }

    private boolean isInvalidPins(Pin pin) {
        return getSum() + pin.getPin() > MAXIMUM_PINS;
    }

    private int getSum() {
        return pins.stream()
                    .mapToInt(Pin::getPin)
                    .sum();
    }
}