package step3.state;

import step3.domain.dto.PointDTO;

import java.util.Arrays;

public interface Symbol {
    String NO_MARK = "";

    String getSymbol();

    int getPoint();

    static boolean supported(PointDTO dto) {
        return false;
    }

    static Symbol defaultSymbol() {
        return new EmptySymbol(0);
    }

    static boolean isDouble(int first, int second) {
        return isStrike(first) && isStrike(second);
    }

    static boolean isStrike(int point) {
        return point == 10;
    }

    static boolean isSpare(int first, int second) {
        return first != 10 && Integer.sum(first, second) == 10;
    }

    static boolean isMiss(int point) {
        return 0 < point && point < 10;
    }

    static boolean isMiss(int first, int second) {
        return isMiss(first) && isMiss(second) && Integer.sum(first, second) < 10;
    }

    static boolean isGutter(int point) {
        return point == 0;
    }

    static void isValid(PointDTO dto) {
        int first = dto.getFirst(),
                second = dto.getSecond(),
                third = dto.getThird();

        Arrays.asList(first, second, third)
                .forEach(Symbol::checkRange);
    }

    static void checkRange(int value) {
        if (value > 10) {
            throw new IllegalArgumentException();
        }
    }
}
