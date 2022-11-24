package bowling;

public class CannotCalculateException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public CannotCalculateException() {
        super("시도 횟수가 남아있어 점수 계산이 불가능합니다.");
    }
}
