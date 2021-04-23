package bowling.domain.pin;

public class BonusPin {

    private final Pin value;

    private BonusPin(final Pin bonusPin) {
        this.value = bonusPin;
    }

    public static BonusPin of(final int bonusPin) {
        return new BonusPin(Pin.of(bonusPin));
    }

    public int score() {
        return value.value();
    }
}
