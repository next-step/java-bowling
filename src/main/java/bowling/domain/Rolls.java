package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Rolls {
    private static final int MAX_ROLL_COUNT = 3;
    private int rollCount = 2;
    private List<Pin> rolls = new ArrayList<>();

    public Rolls() {
    }

    public Rolls(List<Pin> rolls) {
        validateRolls();
        this.rolls = Collections.unmodifiableList(new ArrayList<>(rolls));
    }

    public void validateRolls() {
        if (rolls.size() >= rollCount) {
            throw new IllegalArgumentException("던질 수 있는 횟수를 초과하였습니다.");
        }
    }

    public void addBonusRoll() {
        if (rollCount >= MAX_ROLL_COUNT) {
            throw new IllegalArgumentException("최대 공을 던질 수 있는 기회는 3번입니다.");
        }
        ++rollCount;
    }

    public void add(Pin pin) {
        validateRolls();
        rolls.add(pin);
    }

    public List<Pin> getRolls() {
        return rolls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof Rolls)) { return false; }
        final Rolls rolls1 = (Rolls) o;
        return Objects.equals(getRolls(), rolls1.getRolls());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRolls());
    }
}
