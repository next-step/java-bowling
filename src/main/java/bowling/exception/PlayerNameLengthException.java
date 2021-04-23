package bowling.exception;

public final class PlayerNameLengthException extends BowlingException {

    public static final String PLAYER_NAME_RANGE_MESSAGE = "사용자의 이름은 1~3자여야 합니다. 입력된 이름: ";

    public PlayerNameLengthException(String name) {
        super(PLAYER_NAME_RANGE_MESSAGE + name);
    }

}
