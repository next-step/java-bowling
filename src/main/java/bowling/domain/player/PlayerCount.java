package bowling.domain.player;

import bowling.exception.InvalidPlayerCountException;

public final class PlayerCount {

    private static final int MINIMUM_PLAYER_COUNT = 1;

    private final int playerCount;
    private final int current;

    public PlayerCount(final int playerCount) {
        this(playerCount, MINIMUM_PLAYER_COUNT);
    }

    public PlayerCount(final int playerCount, final int current) {
        validatePinCount(playerCount);
        this.playerCount = playerCount;
        this.current = current;
    }

    private final void validatePinCount(final int playerCount) {
        if (playerCount < MINIMUM_PLAYER_COUNT) {
            throw new InvalidPlayerCountException(playerCount);
        }
    }
}
