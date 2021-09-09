package bowling.exception;

public class NameException extends RuntimeException {
    private static final String PLAYER_NAME_EXCEPTION = "이름(%s)을 잘못 입력 했습니다.";

    public NameException(String name) {
        super(String.format(PLAYER_NAME_EXCEPTION, name));
    }
}
