package bowling.exception;

public class CannotCalculateException extends IllegalArgumentException {

    private static final String CANNOT_CALCULATE = "게임이 진행 중이어서 점수를 구할 수 없습니다.";

    public CannotCalculateException() {
        super(CANNOT_CALCULATE);
    }
}
