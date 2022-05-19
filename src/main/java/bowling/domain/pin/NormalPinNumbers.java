package bowling.domain.pin;

import bowling.domain.frameresult.FrameResult;
import bowling.domain.util.PinNoPrinter;

public class NormalPinNumbers implements PinNumbers {

    private final PinNo firstNo;
    private PinNo secondNo;

    public NormalPinNumbers(int firstNo) {
        this.firstNo = PinNo.of(firstNo);
    }

    @Override
    public boolean isFull() {
        return firstNo.isMaxNo() || secondNo != null;
    }

    @Override
    public void addPin(int pinNo) {
        if (firstNo.isMaxNo()) {
            throw new IllegalStateException("can't add extra pin at strike");
        }
        if (!firstNo.canPlus(pinNo)) {
            throw new IllegalStateException("can't add pin");
        }
        this.secondNo = PinNo.of(pinNo);
    }

    @Override
    public String expression() {
        return PinNoPrinter.print(firstNo, secondNo);
    }

    @Override
    public FrameResult result() {
        return firstNo.plus(secondNo);
    }

    @Override
    public int spareBonus() {
        return firstNo.getNo();
    }

    @Override
    public int strikeBonus() {
        return firstNo.getNo() + secondNo.getNo();
    }
}
