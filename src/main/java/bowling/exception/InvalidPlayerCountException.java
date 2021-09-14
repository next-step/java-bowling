package bowling.exception;

public class InvalidPlayerCountException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String INVALID_PLAYER_COUNT = "플레이어 수는 0보다 커야 합니다 -> %d";

    public InvalidPlayerCountException(final int count) {
        super(String.format(INVALID_PLAYER_COUNT, count));
    }

}
