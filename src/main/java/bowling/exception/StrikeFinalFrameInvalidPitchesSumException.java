package bowling.exception;

public class StrikeFinalFrameInvalidPitchesSumException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final String SINGLE_STRIKE_INVALID_PITCHES_SUM = "1번째 투구만 스트라이크인 경우 모든 투구의 합은 %d ~ %d 의 값이어야 합니다";

    public StrikeFinalFrameInvalidPitchesSumException(int over, int below) {
        super(String.format(SINGLE_STRIKE_INVALID_PITCHES_SUM, over, below));
    }

}
