package step2.state;

import step2.domain.dto.PointDTO;

import static step2.state.Symbol.isGutter;
import static step2.state.Symbol.isStrike;

public class GutterSymbol implements Symbol {
    private final int point;

    public GutterSymbol(int point) {
        this.point = point;
    }

    @Override
    public String getSymbol() {
        return "-";
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
            return isGutter(third);
        }

        if (currentSize == 2 && maxPitches == 3) {
            return isGutter(second);
        }

        if (currentSize == 2) {
            return isGutter(second) && !isStrike(first);
        }

        return isGutter(first);
    }
}
