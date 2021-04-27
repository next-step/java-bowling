package bowling.domain.exception;

public class PinsCountException extends IllegalArgumentException {
    private static final String MESSAGE = "넘어뜨린 핀수는 0에서 10 사이의 정수여야 합니다.";
    public PinsCountException() {
        super(MESSAGE);
    }
}
