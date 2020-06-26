package bowling.domain;

public class FallenPinsSumException extends RuntimeException {
    public static final String MESSAGE = "넘어뜨린 핀의 합은 10을 넘을 수 없습니다.";

    public FallenPinsSumException() {
        super(MESSAGE);
    }
}
