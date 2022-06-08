package bowling.exception;

public class NotCreateStateException extends IllegalArgumentException {

    private static final String MESSAGE = "Hit 수가 적절하지 않습니다. Hit: %d";

    public NotCreateStateException(int hit) {
        super(String.format(MESSAGE, hit));
    }
}
