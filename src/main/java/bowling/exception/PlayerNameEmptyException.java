package bowling.exception;

public final class PlayerNameEmptyException extends IllegalArgumentException{

    private static final String MESSAGE = "플레이어 이름을 입력해 주세요.";

    public PlayerNameEmptyException() {
        super(MESSAGE);
    }
}