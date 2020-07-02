package bowling.exception;

public class PlayerCountOutOfRangeException extends RuntimeException {

    private static final String RANGE_OF_PLAYER_COUNT = "플레이어 수는 최소 1명 이상입니다. playerCount: %d";

    public PlayerCountOutOfRangeException(final int count) {
        super(String.format(RANGE_OF_PLAYER_COUNT, count));
    }
}
