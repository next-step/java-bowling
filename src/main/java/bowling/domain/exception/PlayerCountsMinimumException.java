package bowling.domain.exception;

public class PlayerCountsMinimumException extends IllegalArgumentException {
    private static final String INVALID_PLAYER_COUNTS = "게임 참가자는 최소 1명 이상입니다.";

    public PlayerCountsMinimumException() {
        super(INVALID_PLAYER_COUNTS);
    }
}
