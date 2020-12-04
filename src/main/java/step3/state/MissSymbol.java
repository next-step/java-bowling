package step3.state;

import step3.domain.dto.PointDTO;
import static step3.state.Symbol.*;

public class MissSymbol implements Symbol {
    private final int point;

    public MissSymbol(int point) {
        this.point = point;
    }

    @Override
    public String getSymbol() {
        return String.valueOf(point);
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
            return !isMiss(first, second) && isMiss(third);
        }

        if (currentSize == 2 && maxPitches == 3) {
            return isStrike(first) && isMiss(second);
        }

        if (currentSize == 2 && maxPitches == 2) {
            return !isStrike(first) && isMiss(second);
        }
        return isMiss(first);
    }
}
