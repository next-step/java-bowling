package bowling.domain.player;

import bowling.exception.InvalidPlayerCountException;

public final class PlayerCount {

    public static final int MINIMUM = 1;
    private final int playerCount;

    public PlayerCount(final int playerCount) {
        validatePinCount(playerCount);
        this.playerCount = playerCount;
    }

    private final void validatePinCount(final int playerCount) {
        if (playerCount < MINIMUM) {
            throw new InvalidPlayerCountException(playerCount);
        }
    }
}
