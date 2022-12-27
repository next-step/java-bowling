package bowling.domain;

public class Pins {

    public static final int MIN_AMOUNT = 0;
    public static final int MAX_AMOUNT = 10;

    private final int amount;

    public Pins(int amount) {
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

    public boolean isMax() {
        return amount == MAX_AMOUNT;
    }

    public boolean isClear(Pins pins) {
        return amount + pins.amount == MAX_AMOUNT;
    }
}
