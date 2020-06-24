package bowling.domain;

public class IncorrectPlayerNameException extends RuntimeException {
    public static final String MESSAGE = "플레이어 이름은 영어로 3자까지 가능합니다.";

    public IncorrectPlayerNameException() {
        super(MESSAGE);
    }
}
