package bowling.domain.frame;

public class FinalRollingsException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "모든 투구가 완료되어 투구하실 수 없습니다.";

    public FinalRollingsException() {
        super(DEFAULT_MESSAGE);
    }
}
