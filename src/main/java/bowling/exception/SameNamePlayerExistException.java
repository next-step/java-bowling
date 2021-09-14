package bowling.exception;

public class SameNamePlayerExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String DIFFERENT_NAME_PLAYERS = "플레이어 이름은 모두 달라야 합니다";

    public SameNamePlayerExistException() {
        super(DIFFERENT_NAME_PLAYERS);
    }
}
