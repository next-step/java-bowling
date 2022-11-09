package bowling.domain;

public class Pin {
    public static final int MIN_COUNT_OF_PIN = 0;
    public static final int MAX_COUNT_OF_PIN = 10;
    private final int countOfPin;

    public Pin(int countOfPin) {
        if (countOfPin < MIN_COUNT_OF_PIN || countOfPin > MAX_COUNT_OF_PIN) {
            throw new IllegalArgumentException("0에서 10사이의 숫자를 입력해야 합니다.");
        }
        this.countOfPin = countOfPin;
    }

    public static Pin of(String countOfPin) {
        return new Pin(Integer.parseInt(countOfPin));
    }

    public static Pin sumBowls(Pin first, Pin second) {
        int sum = first.countOfPin + second.countOfPin;
        if(sum > MAX_COUNT_OF_PIN) {
            throw new IllegalArgumentException(MAX_COUNT_OF_PIN - first.countOfPin + "이내의 숫자를 입력해야 합니다.");
        }
        return new Pin(sum);
    }

    public int getCountOfPin() {
        return countOfPin;
    }

    public boolean isAllPinsDown() {
        return countOfPin == MAX_COUNT_OF_PIN;
    }


}
