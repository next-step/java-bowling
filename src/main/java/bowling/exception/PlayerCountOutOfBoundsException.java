package bowling.exception;

public class PlayerCountOutOfBoundsException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String PLAYER_COUNT_OUT_OF_BOUNDS =
            "플레이어 수보다 요청하는 순서가 더 큽니다 (요청 가능: 0 ~ %d, 현재 요청: %d)";

    public PlayerCountOutOfBoundsException(final int max, final int count) {
        super(String.format(PLAYER_COUNT_OUT_OF_BOUNDS, max, count));
    }
}
