package bowling.domain.pin;

import bowling.domain.frameresult.FrameResult;
import bowling.domain.frameresult.Miss;
import bowling.domain.util.PinNoPrinter;

import java.util.ArrayList;
import java.util.List;

public class FinalPinNumbers implements PinNumbers {

    private static final int INIT_SIZE = 1;
    public static final int MIN_SIZE = 2;
    private static final int EXTRA_SIZE = 3;

    private final List<PinNo> pinNos = new ArrayList<>();

    public FinalPinNumbers(int pinNo) {
        pinNos.add(PinNo.of(pinNo));
    }

    @Override
    public boolean isFull() {
        if (size() < MIN_SIZE) {
            return false;
        }
        PinNo firstPin = pinNos.get(0);
        PinNo secondPin = pinNos.get(1);
        return firstPin.plus(secondPin) instanceof Miss || size() == EXTRA_SIZE;
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

    public int size() {
        return pinNos.size();
    }

    @Override
    public String toExpression() {
        return PinNoPrinter.print(pinNos);
    }

    @Override
    public FrameResult getResult() {
        PinNo firstPin = pinNos.get(0);
        PinNo secondPin = pinNos.get(1);
        return firstPin.plus(secondPin);
    }

    @Override
    public int spareBonus() {
        return getNo(0);
    }

    @Override
    public int strikeBonus() {
        return getNo(0) + getNo(1);
    }

    public int getOwnSpareBonus() {
        return getNo(2);
    }

    public int getOwnStrikeBonus() {
        return getNo(1) + getNo(2);
    }

    private int getNo(int index) {
        return pinNos.get(index).getNo();
    }

}
