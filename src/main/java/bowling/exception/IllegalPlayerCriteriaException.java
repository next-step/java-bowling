package bowling.exception;

public class IllegalPlayerCriteriaException extends IllegalArgumentException {
    private static final String message = "플레이어의 이름은 3글자로 되어야합니다. (입력된 플레이어: %s)";

    public IllegalPlayerCriteriaException(String value) {
        super(String.format(message, value));
    }
}
