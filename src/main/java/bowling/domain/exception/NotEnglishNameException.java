package bowling.domain.exception;

public class NotEnglishNameException extends RuntimeException {

    public NotEnglishNameException() {
        super("영어 이름이 아닙니다.");
    }
}
