package bowling.domain.score;

import bowling.exception.score.BonusPinValueException;
import bowling.exception.score.SecondPinValueException;
import java.util.Objects;

public class FinalScore implements Score {

    private static final Pin STRIKE = Pin.of(10);

    private final Pin first;
    private final Pin second;
    private final Pin bonus;

    private FinalScore(Pin first, Pin second, Pin bonus) {
        this.first = first;
        this.second = second;
        this.bonus = bonus;
    }

    static Score of(Pin first, Pin second, Pin bonus) {
        return new FinalScore(first, second, bonus);
    }

    public static Score emptyScore() {
        return new FinalScore(null, null, null);
    }

    @Override
    public Score saveNextPin(Pin pin) {
        if (Objects.isNull(first)) {
            return new FinalScore(pin, null, null);
        } else if (Objects.isNull(second)) {
            checkRemainPin(pin);
            return new FinalScore(first, pin, null);
        } else if (Objects.isNull(bonus)) {
            checkBonusPin();
            return new FinalScore(first, second, pin);
        }
        return null;
    }

    private void checkRemainPin(Pin pin) {
        if (first != STRIKE && first.remainPin() < pin.value()) {
            throw new SecondPinValueException();
        }
    }

    private void checkBonusPin() {
        if (!isBonus()) {
            throw new BonusPinValueException();
        }
    }

    private boolean isBonus() {
        return (first == STRIKE && second == STRIKE) || (first.remainPin() == second.value());
    }

    @Override
    public boolean isNextStorable() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FinalScore that = (FinalScore) o;
        return Objects.equals(first, that.first) && Objects.equals(second, that.second)
            && Objects.equals(bonus, that.bonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, bonus);
    }

}
