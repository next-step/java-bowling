package bowling.presentation;

import bowling.domain.frame.FallenPins;

import static bowling.presentation.ScoreFormat.*;
import static java.math.BigInteger.TEN;

// board에 출력되는 format을 만드는 객체
public class Format {
    public static String fallenPinsFormat(FallenPins fallenPins) {
        if (fallenPins.size() == 1) {
            return singlePinInBoard(fallenPins);
        }
        if (fallenPins.size() == 2) {
            return doublePinInBoard(fallenPins);
        }
        if (fallenPins.size() == 3) {
            return triplePinInBoard(fallenPins);
        }
        return String.format(centerAlignFormat(6), "");
    }

    private static String singlePinInBoard(FallenPins fallenPins) {
        return String.format(centerAlignFormat(3), singlePinFormat(fallenPins.first()));
    }

    private static String singlePinFormat(int score) {
        return TEN.intValue() == score ? STRIKE.format() : String.valueOf(score);
    }

    private static String doublePinInBoard(FallenPins fallenPins) {
        return String.format(centerAlignFormat(4), doublePinFormat(fallenPins.first(), fallenPins.second()));
    }

    private static String doublePinFormat(int first, int second) {
        if (first + second == TEN.intValue()) {
            return first + DELIMITER.format() + SPARE.format();
        }
        if (second == 0) {
            return first + DELIMITER.format() + GUTTER.format();
        }
        return singlePinFormat(first) + DELIMITER.format() + singlePinFormat(second);
    }

    private static String triplePinInBoard(FallenPins fallenPins) {
        return String.format(centerAlignFormat(5), triplePinFormat(fallenPins.first(), fallenPins.second(), fallenPins.third()));
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

    private static String centerAlignFormat(int size) {
        int MAX_WIDTH = 6;
        if (size > MAX_WIDTH) {
            throw new IllegalArgumentException(String.format("size는 %d보다 작아야 합니다. - %d", MAX_WIDTH, size));
        }
        return "%" + size + "s" + " ".repeat(MAX_WIDTH - size);
    }
}
