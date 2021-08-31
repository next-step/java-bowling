package bowling.exception;

public class CannotCalculateException extends RuntimeException {
    public CannotCalculateException() {
        super("점수를 계산할 수 없습니다.");
    }
}
