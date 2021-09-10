package step2.exceptions;

public class BowlingPintNumberError extends IllegalArgumentException {
    private static final String BOWLING_PIN_NUMBER_ERROR_MESSAGE = "쓰러뜨린 볼린공의 개수는 10개 이하로 지정하여야 합니다.";
    public BowlingPintNumberError() {
        super(BOWLING_PIN_NUMBER_ERROR_MESSAGE);
    }
}
