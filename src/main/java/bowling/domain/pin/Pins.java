package bowling.domain.pin;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.ArrayList;
import java.util.List;

public class Pins {
    private static final int PINS_LIMIT = 10;

    private static final int FIRST_FRAME = 1;
    private static final int FINAL_FRAME = 10;
    private static final String PIN_MAX_ERROR = "핀의 합계가 10개보다 클 수 없습니다.";
    private static final int CAN_ROLL_LIMIT = 2;
    private static final int FIRST_ROLL = 1;

    private List<Pin> pins;

    public Pins() {
        this.pins = new ArrayList<>();
    }

    public Pins(List<Pin> pins) {
        this.pins = pins;
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


    public boolean isRolledTwice() {
        return pins.size() >= CAN_ROLL_LIMIT;
    }

    public boolean isAlreadyStrike() {
        return pins.size() == FIRST_ROLL && this.getTotalPins() == PINS_LIMIT;
    }

    private boolean isPinTotalOverTen(int pin) {
        return this.getTotalPins() + pin > PINS_LIMIT;
    }

    public boolean isPinReady() { return !isAlreadyStrike() && !isRolledTwice();}

    public void addPins(Frame frame, int pin) {

        if (frame instanceof NormalFrame && isPinTotalOverTen(pin)) {
            throw new IllegalArgumentException(PIN_MAX_ERROR);
        }

        this.pins.add(new Pin(pin));
    }

    public List<Pin> getPins() {
        return pins;
    }

    public int getPinCount(int index) {
        return pins.get(index).getPin();
    }

    public int rollCount() {
        return this.pins.size();
    }



}
