package bowling.exception;

public class PlayerNameLengthException extends  IllegalArgumentException{

    public static final String PLAYER_NAME_LENGTH = "사람의 이름은 3글자를 초과할 수 없습니다.";

    public PlayerNameLengthException() {
        super(PLAYER_NAME_LENGTH);
    }
}
