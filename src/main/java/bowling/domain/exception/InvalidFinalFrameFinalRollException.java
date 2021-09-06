package bowling.domain.exception;

public class InvalidFinalFrameFinalRollException extends RuntimeException {

    public InvalidFinalFrameFinalRollException() {
        super("마지막 프레임의 마지막 세번째 투구는 이전이 스트라이크 이거나 스페어야 합니다.");
    }
}
