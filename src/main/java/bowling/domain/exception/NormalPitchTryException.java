package bowling.domain.exception;

public class NormalPitchTryException extends IllegalStateException {
    private static final String INVALID_NORMAL_PITCH_TRY = "1~9번 프레임은 2번을 초과하여 투구할 수 없습니다.";

    public NormalPitchTryException() {
        super(INVALID_NORMAL_PITCH_TRY);
    }
}
