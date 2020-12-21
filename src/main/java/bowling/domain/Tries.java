package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Tries {
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int THIRD_INDEX = 2;

    private final List<Try> tries;

    private Tries(List<Try> tries) {
        this.tries = tries;
    }

    public static Tries init() {
        return new Tries(new ArrayList<>());
    }

    public boolean isStrike() {
        if(isFirstNotThrown()) {
            return false;
        }

        return Try.MAX.equals(first().get());
    }

    public boolean isSpare() {
        if(isSecondNotThrown()) {
            return false;
        }
        return Try.MAX.equals(first().get().plus(second().get()));
    }

    public int size() {
        return tries.size();
    }

    public void add(int value) {
        tries.add(Try.of(value));
    }

    public Optional<Try> first() {
        Try t = null;
        if(isFirstNotThrown()) {
            return Optional.ofNullable(t);
        }

        return Optional.of(tries.get(FIRST_INDEX));
    }

    public Optional<Try> second() {
        Try t = null;
        if(isSecondNotThrown()) {
            return Optional.ofNullable(t);
        }

        return Optional.of(tries.get(SECOND_INDEX));
    }

    public Optional<Try> third() {
        Try t = null;
        if(isThirdNotThrown()) {
            return Optional.ofNullable(t);
        }

        return Optional.of(tries.get(THIRD_INDEX));
    }

    public boolean isFirstNotThrown() {
        return tries.size() == FIRST_INDEX;
    }

    public boolean isSecondNotThrown() {
        return tries.size() <= SECOND_INDEX;
    }

    public boolean isThirdNotThrown() {
        return tries.size() <= THIRD_INDEX;
    }
}
