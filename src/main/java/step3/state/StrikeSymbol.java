package step3.state;

import step3.domain.dto.PointDTO;

import static step3.state.Symbol.*;

public class StrikeSymbol implements Symbol {
    private final int point;

    public StrikeSymbol(int point) {
        this.point = point;
    }

    @Override
    public int getPoint() {
        return point;
    }

    public static boolean supported(PointDTO dto) {
        Symbol.isValid(dto);
        int currentSize = dto.getCurrentSize();
        int maxPitches = dto.getMaxPitches();
        int first = dto.getFirst(),
                second = dto.getSecond(),
                third = dto.getThird();

        if (currentSize == 3) {
            return (isSpare(first, second) || isDouble(first, second)) && isStrike(third);
        }

        if (currentSize == 2 && maxPitches == 3) {
            return isDouble(first, second);
        }

        if (currentSize == 1) {
            return isStrike(first);
        }


        return false;
    }


    @Override
    public String getSymbol() {
        return "X";
    }

}
