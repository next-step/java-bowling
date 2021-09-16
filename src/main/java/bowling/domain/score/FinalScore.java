package bowling.domain.score;

import bowling.exception.score.PinBonusException;
import bowling.exception.score.PinSaveExcessException;
import bowling.exception.score.PinSecondValueException;
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
            return createFirstPin(pin);
        }
        if (Objects.isNull(second)) {
            return createSecondPin(pin);
        }
        if (Objects.isNull(bonus)){
            return createBonusPin(pin);
        }
        throw new PinSaveExcessException();
    }

    private FinalScore createFirstPin(Pin pin) {
        return new FinalScore(pin, null, null);
    }

    private FinalScore createSecondPin(Pin pin) {
        checkRemainPin(first, pin);
        return new FinalScore(first, pin, null);
    }

    private static void checkRemainPin(Pin before, Pin pin) {
        if (before != STRIKE && before.remainPin() < pin.value()) {
            throw new PinSecondValueException();
        }
    }

    private FinalScore createBonusPin(Pin pin) {
        checkBonusPin(this);
        return new FinalScore(first, second, pin);
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
        return Objects.isNull(first) || Objects.isNull(second)
            || (first == STRIKE && second == STRIKE) || (first.remainPin() == second.value());
    }

}
