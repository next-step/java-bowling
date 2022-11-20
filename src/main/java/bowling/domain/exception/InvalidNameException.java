package bowling.domain.exception;

public class InvalidNameException extends RuntimeException {

    public InvalidNameException() {
        super("이름은 알파벳 세 글자로 구성되어야 합니다.");
    }

}
