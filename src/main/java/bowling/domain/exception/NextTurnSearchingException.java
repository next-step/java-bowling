package bowling.domain.exception;

public class NextTurnSearchingException extends RuntimeException {
    private static final String CANNOT_FIND_GAME_TURN = "플레이할 수 있는 다음 턴 플레이어를 찾을 수 없습니다.";

    public NextTurnSearchingException() {
        super(CANNOT_FIND_GAME_TURN);
    }
}
