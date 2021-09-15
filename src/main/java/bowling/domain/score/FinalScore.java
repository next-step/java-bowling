package bowling.domain.score;

import bowling.exception.Pin.PinBonusException;
import bowling.exception.Pin.PinSecondValueException;
import java.util.Objects;

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
    public FinalScore nextPin(Pin pin) {
        if (Objects.isNull(first)) {
            return new FinalScore(pin, null, null);
        }
        if (Objects.isNull(second)) {
            checkRemainPin(this.first, pin);
            return new FinalScore(this.first, pin, null);
        }
        checkBonusPin(this);
        return new FinalScore(this.first, this.second, pin);
    }

    private static void checkRemainPin(Pin before, Pin pin) {
        if (before != STRIKE && before.remainPin() < pin.value()) {
            throw new PinSecondValueException();
        }
    }

    private static void checkBonusPin(FinalScore finalScore) {
        if (!finalScore.isBonus()) {
            throw new PinBonusException();
        }
    }

    private boolean isBonus() {
        return (first == STRIKE && second == STRIKE) || (first.remainPin() == second.value());
    }

    @Override
    public boolean isNext() {
        return false;
    }

}
