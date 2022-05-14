package bowling.domain.pin;

import bowling.domain.util.PinNoPrinter;

public class PinNumbers {

    private final PinNo firstNo;
    private PinNo secondNo;

    public PinNumbers(int firstNo) {
        this.firstNo = PinNo.of(firstNo);
    }

    public boolean isFull() {
        return firstNo.isMaxNo() || secondNo != null;
    }

    public void addPin(int pinNo) {
        if (firstNo.isMaxNo()) {
            throw new IllegalStateException("can't add extra pin at strike");
        }
        if (!firstNo.canPlus(pinNo)) {
            throw new IllegalStateException("can't add pin");
        }
        this.secondNo = PinNo.of(pinNo);
    }

    public String toExpression() {
        return PinNoPrinter.print(firstNo, secondNo);
    }
}
