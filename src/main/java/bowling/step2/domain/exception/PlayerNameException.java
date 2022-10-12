package bowling.step2.domain.exception;

public class PlayerNameException extends IllegalArgumentException {
    private static final String PLAYER_NAME_FORMAT_EXCEPTION_MESSAGE = "플레이어 이름 형식이 잘못되었습니다. 다시 입력해주세요.";
    
    public PlayerNameException() {
        super(PLAYER_NAME_FORMAT_EXCEPTION_MESSAGE);
    }
}
