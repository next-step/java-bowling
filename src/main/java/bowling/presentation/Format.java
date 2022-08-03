package bowling.presentation;

import java.util.List;

import static bowling.presentation.PinFormat.*;
import static java.math.BigInteger.TEN;
import static java.math.BigInteger.ZERO;

// board에 출력되는 format을 만드는 객체
public class Format {
    public static String fallenPinsFormat(List<Integer> fallenPins) {
        if (fallenPins.size() == 1) {
            return singlePinInBoard(fallenPins);
        }
        if (fallenPins.size() == 2) {
            return doublePinInBoard(fallenPins);
        }
        if (fallenPins.size() == 3) {
            return triplePinInBoard(fallenPins);
        }
        return emptyFormat();
    }

    public static String emptyFormat() {
        return String.format(centerAlignFormat(6), "");
    }

    private static String singlePinInBoard(List<Integer> fallenPins) {
        return String.format(centerAlignFormat(3), singlePinFormat(fallenPins.get(0)));
    }

    private static String singlePinFormat(int pin) {
        if (TEN.intValue() == pin) {
            return STRIKE.format();
        }
        if (ZERO.intValue() == pin) {
            return GUTTER.format();
        }
        return String.valueOf(pin);
    }

    private static String doublePinInBoard(List<Integer> fallenPins) {
        return String.format(centerAlignFormat(4), doublePinFormat(fallenPins.get(0), fallenPins.get(1)));
    }

    private static String doublePinFormat(int first, int second) {
        if (first + second == TEN.intValue()) {
            return first + DELIMITER.format() + SPARE.format();
        }
        return singlePinFormat(first) + DELIMITER.format() + singlePinFormat(second);
    }

    private static String triplePinInBoard(List<Integer> fallenPins) {
        return String.format(centerAlignFormat(5), triplePinFormat(fallenPins.get(0), fallenPins.get(1), fallenPins.get(2)));
    }

    private static String triplePinFormat(int first, int second, int third) {
        if (first == TEN.intValue() && second == TEN.intValue()) {
            return STRIKE.format() + DELIMITER.format() + STRIKE.format() + DELIMITER.format() + singlePinFormat(third);
        }
        if (first == TEN.intValue()) {
            return STRIKE.format() + DELIMITER.format() + doublePinFormat(second, third);
        }
        return doublePinFormat(first, second) + DELIMITER.format() + singlePinFormat(third);
    }

    public static String nameProperty() {
        return DELIMITER.format() + String.format(centerAlignFormat(5), "NAME") + DELIMITER.format();
    }

    public static String frameIndex(int index) {
        return String.format(centerAlignFormat(4), String.format("%02d", index)) + DELIMITER.format();
    }

    public static String playerName(String name) {
        return DELIMITER.format() + String.format(centerAlignFormat(4), name) + DELIMITER.format();
    }

    public static String scoreName(String name) {
        return DELIMITER.format() + String.format(centerAlignFormat(5), name) + DELIMITER.format();
    }

    private static String centerAlignFormat(int size) {
        int MAX_WIDTH = 6;
        if (size > MAX_WIDTH) {
            throw new IllegalArgumentException(String.format("size는 %d보다 작아야 합니다. - %d", MAX_WIDTH, size));
        }
        return "%" + size + "s" + " ".repeat(MAX_WIDTH - size);
    }

    public static String scoreFormat(int score) {
        return score == 0
                ? String.format(centerAlignFormat(6), "")
                : String.format(centerAlignFormat(3), String.format("%3d", score));
    }
}
