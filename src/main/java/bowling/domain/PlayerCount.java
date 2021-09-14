package bowling.domain;

import bowling.exception.InvalidPlayerCountException;

public class PlayerCount {

    private final int ZERO = 0;

    private final int count;

    public PlayerCount(final int count) {
        validateCount(count);
        this.count = count;
    }

    public int count() {
        return count;
    }

    private void validateCount(final int count) {
        if (count <= ZERO) {
            throw new InvalidPlayerCountException(count);
        }
    }
}
