package bowling.domain.exception;

public class InvalidSecondRollException extends RuntimeException {

    public InvalidSecondRollException() {
        super("두번째 투구는 첫번째 투구가 스트라이크가 아니면 합산이 핀의 개수를 넘을 수 없습니다.");
    }
}
