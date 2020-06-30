package bowling.domain.exception;

public class NextPitchScoreTypeException extends IllegalStateException {
    private static final String CANNOT_CONTINUE_NEXT_PITCH = "이전 투구의 결과가 스페어나 스트라이크인 경우, 이어서 투구할 수 없습니다.";

    public NextPitchScoreTypeException() {
        super(CANNOT_CONTINUE_NEXT_PITCH);
    }
}
