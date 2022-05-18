package bowling.exception;

public class MaximumSumExceededException extends RuntimeException {
    private static final String MESSAGE = "ERROR] 1차 및 2차 시도의 합은 10을 초과할 수 없습니다(1차: %s, 2차: %s).";

    public MaximumSumExceededException(int first, int second) {
        super(String.format(MESSAGE, first, second));
    }
}
