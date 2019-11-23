package bowling.score.rollling;

import java.util.Arrays;

public enum Pin {
    ZERO(0, "-"),
    OME(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "X");
    public static final int MAX_PIN_NUMBER = Arrays.stream(Pin.values())
            .map(scoreType -> scoreType.number)
            .max(Integer::compareTo)
            .orElseThrow(IllegalArgumentException::new);
    public static final int MIN_PIN_NUMBER = Arrays.stream(Pin.values())
            .map(scoreType -> scoreType.number)
            .min(Integer::compareTo)
            .orElseThrow(IllegalArgumentException::new);
    private int number;
    private String numberString;

    Pin(int number, String numberString) {
        this.number = number;
        this.numberString = numberString;
    }

    public static String toString(int number) {
        return Arrays.stream(values())
                .filter(scoreType -> scoreType.getNumber() == number)
                .findFirst()
                .map(Pin::getNumberString)
                .orElseThrow(IllegalArgumentException::new);
    }

    private int getNumber() {
        return number;
    }

    private String getNumberString() {
        return numberString;
    }
}
