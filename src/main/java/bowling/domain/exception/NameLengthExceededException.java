package bowling.domain.exception;

public class NameLengthExceededException extends RuntimeException {

    public NameLengthExceededException() {
        super("이름은 3글자를 초과할 수 없습니다.");
    }
}
