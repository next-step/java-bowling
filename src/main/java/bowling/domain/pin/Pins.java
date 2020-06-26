package bowling.domain.pin;

import java.util.ArrayList;
import java.util.List;

public class Pins {
    private static final int PINS_LIMIT = 10;
    public static final String PIN_MAX_ERROR = "핀의 합계가 10개보다 클 수 없습니다.";

    private List<Pin> pins;

    public Pins() {
        this.pins = new ArrayList<>();
    }

    public int getTotalPins() {
        return this.pins.stream()
                .map(Pin::getPin)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getLeftPin() {
        return PINS_LIMIT - getTotalPins();
    }

    public void addPins(int pin) {
        if (isPinTotalOverTen(pin)) {
            throw new IllegalArgumentException(PIN_MAX_ERROR);
        }

        this.pins.add(new Pin(pin));
    }

    private boolean isPinTotalOverTen(int pin) {
        return this.getTotalPins() + pin > PINS_LIMIT;
    }

    public int rollCount() {
        return this.pins.size();
    }

}
