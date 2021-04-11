package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class PinNumbers {

    private static final int MAX_PIN_NUMBER = 10;
    private final List<PinNumber> pinNumbers;
    private int remainPinNumber;

    public PinNumbers() {
        pinNumbers = new ArrayList<>();
        remainPinNumber = MAX_PIN_NUMBER;
    }

    public PinNumber index(int index) {
        return pinNumbers.get(index);
    }

    public int size() {
        return pinNumbers.size();
    }

    public void record(PinNumber pinNumber) {
        if (!pinNumber.isStrike()) {
            updateRemainPinNumber(pinNumber);
        }
        pinNumbers.add(pinNumber);
    }

    private void updateRemainPinNumber(PinNumber pinNumber) {
        remainPinNumber -= pinNumber.pinNumber();
        if (remainPinNumber < 0) {
            throw new IllegalArgumentException();
        }
        if (remainPinNumber == 0) {
            remainPinNumber = MAX_PIN_NUMBER;
        }
    }

    public String state(int index) {
        if (index == 1) {
            return FrameState.eachState(pinNumbers.get(0));
        }
        if (pinNumbers.get(index - 1).pinNumber() == MAX_PIN_NUMBER) {
            return FrameState.STRIKE.frameState();
        }
        return FrameState.pairState(pinNumbers.get(index - 2), pinNumbers.get(index - 1));
    }
}
