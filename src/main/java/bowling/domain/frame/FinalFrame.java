package bowling.domain.frame;

import bowling.domain.pin.PinNo;
import bowling.domain.util.PinNoPrinter;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.pin.PinStatus.plus;

public class FinalFrame implements Frame {

    private static final int INIT_SIZE = 1;
    private static final int MIN_SIZE = 2;
    private static final int EXTRA_SIZE = 3;

    private final List<PinNo> pinNos = new ArrayList<>();

    public FinalFrame(int pinNo) {
        pinNos.add(PinNo.of(pinNo));
    }

    @Override
    public boolean isFull() {
        if (size() < MIN_SIZE) {
            return false;
        }
        PinNo firstPin = pinNos.get(0);
        PinNo secondPin = pinNos.get(1);
        return plus(firstPin, secondPin).isMiss() || size() == EXTRA_SIZE;
    }

    @Override
    public void addPin(int pinNo) {
        if (size() == INIT_SIZE) {
            validateSecondPin(pinNo);
        }
        pinNos.add(PinNo.of(pinNo));
    }

    private void validateSecondPin(int pinNo) {
        PinNo firstPin = pinNos.get(0);
        if (firstPin.isMaxNo()) {
            return;
        }
        if (!firstPin.canPlus(pinNo)) {
            throw new IllegalStateException("can't add pin");
        }
    }

    private int size() {
        return pinNos.size();
    }

    @Override
    public Frame nextFrame(int pinNo) {
        throw new IllegalStateException("current frame is final frame");
    }

    @Override
    public String toExpression() {
        return PinNoPrinter.print(pinNos);
    }
}
