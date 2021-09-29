package bowling.domain.score;

import java.util.Objects;

public class FinalScore implements Score {

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

    @Override
    public Score saveNextPin(Pin pin) {
        return null;
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
