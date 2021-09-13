package bowling.exception;

public class OverScoreException extends Throwable {
    private static String MESSAGE = "점수를 잘못 입력했습니다 총 점수 : %s";
    public OverScoreException(int totalScore) {
        super(String.format(MESSAGE, totalScore));
    }
}
