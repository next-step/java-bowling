package bowling.exception;

public class NoStrikeFinalFrameInvalidPitchesSumException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String NONE_STRIKE_INVALID_PITCHES_SUM = "1번째 투구가 스트라이크가 아닌 경우 모든 투구의 합은 %d 이하여야 합니다";

    public NoStrikeFinalFrameInvalidPitchesSumException(int below) {
        super(String.format(NONE_STRIKE_INVALID_PITCHES_SUM, below));
    }

}
