package bowling.exception;

public class InvalidSpareException extends RuntimeException {

    private static final String MESSAGE = "쓰러뜨린 볼링 핀이 0개인 경우, 스페어가 될 수 없습니다.";

    public InvalidSpareException() {
        super(MESSAGE);
    }
}
