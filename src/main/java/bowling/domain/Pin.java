package bowling.domain;

public class Pin {
    private static final String PIN_COUNT_ERROR_MESSAGE = "error : 핀 개수는 최소 %d 최대 %d 입니다.";
    private static final int MAX_PIN = 10;
    private static final int MIN_PIN = 0;

    private final int score;

    public Pin(int score) {
        validPinNumber(score);
        this.score = score;
    }

    private void validPinNumber(int score) {
        if (score < MIN_PIN || score > MAX_PIN) {
            throw new IllegalArgumentException(String.format(PIN_COUNT_ERROR_MESSAGE, MIN_PIN, MAX_PIN));
        }
    }

    public int value() {
        return score;
    }
}
