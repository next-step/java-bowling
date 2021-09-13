package step3.exceptions;

public class CannotCalculateExceptions extends IllegalArgumentException {

    private static final String CAN_NOT_CALCULATE = "시도 회수가 남아있을 경우 점수를 계산할 수 없습니다.";

    public CannotCalculateExceptions() {
        super(CAN_NOT_CALCULATE);
    }
}
