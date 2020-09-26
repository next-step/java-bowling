package bowling.domain.pin;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.ArrayList;
import java.util.List;

public class Pins {
    private static final String PIN_MAX_ERROR = "핀의 합계가 10개보다 클 수 없습니다.";
    private static final String ROLL_COUNT_ERRORS = "일반 게임에서는 두번만 던질 수 있습니다.";
    private static final int NORMAL_FRAME_CAN_ROLL = 2;
    private static final int FINAL_FRAME_CAN_ROLL = 3;
    private static final int FIRST_ROLL = 1;
    private static final int PINS_LIMIT = 10;

    private List<Pin> pins;

    public Pins() {
        this.pins = new ArrayList<>();
    }

    public Pins(List<Pin> pins) {
        this.pins = pins;
    }

    public void addPins(Frame frame, int pin) {
        if (frame instanceof NormalFrame && isPinTotalOverTen(pin)) {
            throw new IllegalArgumentException(PIN_MAX_ERROR);
        }
        if (frame instanceof FinalFrame && isPinOverflowRolledOnce(pin)) {
            throw new IllegalArgumentException(ROLL_COUNT_ERRORS);
        }
        this.pins.add(new Pin(pin));
    }

    public boolean isPinReady(Frame frame) {
        if (frame instanceof NormalFrame) {
            return !isStrike() && !isRolledTwice();
        }
        if (!isRolledTwice() || getTotalPins() >= PINS_LIMIT) {
            return pins.size() < FINAL_FRAME_CAN_ROLL;
        }
        return false;
    }

    public int getTotalPins() {
        return this.pins.stream()
                .map(Pin::getPin)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private boolean isPinOverflowRolledOnce(int pin) {
        return isRolledOnce() && (isPinUnderTen()) && (getTotalPins() + pin) > PINS_LIMIT;
    }

    public int getLeftPin() {
        return PINS_LIMIT - getTotalPins();
    }

    public boolean isStrike() {
        return pins.size() == FIRST_ROLL && this.getTotalPins() == PINS_LIMIT;
    }

    private boolean isPinTotalOverTen(int pin) {
        return this.getTotalPins() + pin > PINS_LIMIT;
    }

    private boolean isRolledTwice() {
        return pins.size() == NORMAL_FRAME_CAN_ROLL;
    }

    private boolean isRolledOnce() {
        return pins.size() < NORMAL_FRAME_CAN_ROLL;
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

    private boolean isPinUnderTen() {
        return getTotalPins() < PINS_LIMIT;
    }


}
