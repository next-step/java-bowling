package bowling.domain;

import java.util.Objects;

public class Pin {

    public static final int MIN_AMOUNT = 0;
    public static final int MAX_AMOUNT = 10;
    public static final String GUTTER_MESSAGE = "-";

    private final int amount;

    public Pin(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException("볼링 핀의 개수는 0개 이상이어야 합니다.");
        }
        if (amount > MAX_AMOUNT) {
            throw new IllegalArgumentException("볼링 핀의 개수는 10개 이하여야 합니다.");
        }
    }

    public int amount() {
        return amount;
    }

    // todo - Pin 10개 이상 넘어가는지 FirstPin에서 validation 추가
//    public boolean isOverMax(Pin pin) {
//        return
//    }

    public boolean isMax() {
        return amount == MAX_AMOUNT;
    }

    public boolean isClear(Pin pin) {
        return amount + pin.amount == MAX_AMOUNT;
    }

    @Override
    public String toString() {
        if (amount == MIN_AMOUNT) {
            return GUTTER_MESSAGE;
        }
        return String.valueOf(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return amount == pin.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
