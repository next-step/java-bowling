package bowling.domain.pin;

import java.util.Objects;

import static bowling.domain.pin.PinNo.MAX_PIN_NO;

public class PinNumbers {

    private final PinNo firstNo;
    private final PinNo secondNo;

    public PinNumbers(int firstNo, int secondNo) {
        validateNumbers(firstNo, secondNo);
        this.firstNo = PinNo.of(firstNo);
        this.secondNo = PinNo.of(secondNo);
    }

    private void validateNumbers(int firstNo, int secondNo) {
        if (firstNo != MAX_PIN_NO && firstNo + secondNo > MAX_PIN_NO) {
            throw new IllegalArgumentException("invalid pin numbers: " + firstNo + ", " + secondNo);
        }
    }

    public PinStatus getStatus() {
        return PinStatus.getStatus(firstNo, secondNo);
    }

    public int getFirstNo() {
        return firstNo.getNo();
    }

    public int getSecondNo() {
        return secondNo.getNo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PinNumbers that = (PinNumbers) o;
        return Objects.equals(firstNo, that.firstNo) && Objects.equals(secondNo, that.secondNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNo, secondNo);
    }
}
