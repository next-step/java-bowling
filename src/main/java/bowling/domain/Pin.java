package bowling.domain;

public class Pin {
    private final int countOfPin;

    public Pin(int countOfPin) {
        if (countOfPin < 0 || countOfPin > 10) {
            throw new IllegalArgumentException("0에서 10사이의 숫자를 입력해야 합니다.");
        }
        this.countOfPin = countOfPin;
    }

    public static Pin of(String countOfPin) {
        return new Pin(Integer.parseInt(countOfPin));
    }
}
