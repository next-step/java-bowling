package bowling.domain.exception;

public class FinalPitchTryException extends IllegalStateException {
    private static final String INVALID_FINAL_PITCH_TRY = "주어진 마지막 프레임 투구 회수를 초과하여 투구할 수 없습니다.";

    public FinalPitchTryException() {
        super(INVALID_FINAL_PITCH_TRY);
    }
}
