package bowling.step2.domain.exception;

public class BowlException extends IllegalArgumentException {
    private static final String IMPOSSIBLE_BOWL_EXCEPTION = "더이상 투구할 수 없습니다.";
    
    public BowlException() {
        super(IMPOSSIBLE_BOWL_EXCEPTION);
    }
}
