package step2.state;

import step2.domain.dto.PointDTO;

import static step2.state.Symbol.*;

public class SpareSymbol implements Symbol {
    private final int point;

    public SpareSymbol(int point) {
        this.point = point;
    }

    @Override
    public String getSymbol() {
        return "/";
    }

    @Override
    public int getPoint() {
        return point;
    }

    public static boolean supported(PointDTO dto) {
        Symbol.isValid(dto);
        int currentSize = dto.getCurrentSize();
        int first = dto.getFirst(),
            second = dto.getSecond(),
            third = dto.getThird();

        if (currentSize == 3) {
            return !isSpare(first, second) && !isDouble(first, second) && isSpare(second, third);
        }

        if (currentSize == 2) {
            return isSpare(first, second);
        }

        return false;
    }

}
