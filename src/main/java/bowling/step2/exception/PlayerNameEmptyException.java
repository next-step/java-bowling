package bowling.step2.exception;

public class PlayerNameEmptyException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "플레이어 이름에 빈 값이 입력되었습니다.";

    public PlayerNameEmptyException () {
        super(ERROR_MESSAGE);
    }
}