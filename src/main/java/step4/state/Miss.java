package step4.state;

import step4.domain.BowlingPoint;
import step4.domain.dto.PointDTO;
import step4.type.ResultPitchesType;

import static step4.state.Symbol.*;

public class Miss implements Symbol {
    public static ResultPitchesType type = ResultPitchesType.MISS;
    private final BowlingPoint point;

    public Miss(int point) {
        this(BowlingPoint.of(point));
    }

    public Miss(BowlingPoint point) {
        this.point = point;
    }

    @Override
    public String getSymbol() {
        return String.valueOf(getPoint());
    }

    @Override
    public int getPoint() {
        return point.getPoint();
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
