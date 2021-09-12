package bowling.exception;

public class NormalFrameInvalidPitchesSumException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String INVALID_PITCHES_SUM = "모든 투구의 합은 %d 이하여야 합니다";

    public NormalFrameInvalidPitchesSumException(int below) {
        super(String.format(INVALID_PITCHES_SUM, below));
    }

}
