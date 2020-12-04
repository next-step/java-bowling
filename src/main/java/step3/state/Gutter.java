package step3.state;

import step3.domain.dto.PointDTO;
import step3.type.ResultPitchesType;

import static step3.state.Symbol.isGutter;
import static step3.state.Symbol.isStrike;

public class Gutter implements Symbol {
    public static ResultPitchesType type = ResultPitchesType.GUTTER;
    private final int point;

    public Gutter(int point) {
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

    @Override
    public ResultPitchesType getType() {
        return type;
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
