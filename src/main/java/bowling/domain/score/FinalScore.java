package bowling.domain.score;

import bowling.exception.Pin.PinSecondValueException;

public class FinalScore extends Score {

    private static final Pin STRIKE = Pin.of(10);

    private final Pin bonus;

    private FinalScore(Pin first, Pin second, Pin bonus) {
        super(first, second);
        this.bonus = bonus;
    }

    public static FinalScore empty() {
        return new FinalScore(null, null, null);
    }

    @Override
    public FinalScore createFirstPin(Pin pin) {
        return new FinalScore(pin, null, null);
    }

    @Override
    public FinalScore createSecondPin(Pin pin) {
        checkRemainPin(this.first, pin);

        return new FinalScore(this.first, pin, null);
    }

    private static void checkRemainPin(Pin before, Pin pin) {
        if (before != STRIKE && before.remainPin() < pin.value()) {
            throw new PinSecondValueException();
        }
    }

    public FinalScore createBonusPin(Pin pin) {
        checkBonusPin(this);

        return new FinalScore(this.first, this.second, pin);
    }

    private static void checkBonusPin(FinalScore finalScore) {
        if (!finalScore.isBonus()) {
            throw new RuntimeException("보너스 핀은 스페어나 스트라이크 후 칠 수 있다.");
        }
    }

    public boolean isBonus() {
        return (first == STRIKE && second == STRIKE) || (first.remainPin() == second.value());
    }

}
