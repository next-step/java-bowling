package step4.exception;

public class minimumLeftExcpetion extends RuntimeException {
    private static final String MIN_LEFT_EXCEPTION = "시도 회수가 0보다 적을 수는 없습니다.";

    public minimumLeftExcpetion() {
        super(MIN_LEFT_EXCEPTION);
    }
}
