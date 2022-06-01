package bowling.domain.score.exception;

public class DoNotCalculateException extends RuntimeException{

    private static final String EXCEPTION_MESSAGE = "현재 점수를 가져올 수 없습니다. 남은 횟수는 %s 번 남았습니다.";

    public DoNotCalculateException(int left) {
        super(String.format(EXCEPTION_MESSAGE, left));
    }
}
