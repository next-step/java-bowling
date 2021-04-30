package bowling.exception;

public class OutOfBoundNameSize extends IllegalArgumentException {
    private final static String value = "참가자의 이름은 3글자로 입력해 주세요.";

    public OutOfBoundNameSize() {
        super(value);
    }
}
