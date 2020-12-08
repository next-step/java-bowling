package step4.state;

import step4.domain.BowlingPoint;
import step4.domain.dto.PointDTO;
import step4.type.ResultPitchesType;

import static step4.state.Symbol.*;

public class Strike implements Symbol {
    public static ResultPitchesType type = ResultPitchesType.STRIKE;
    private final BowlingPoint point;

    public Strike(int point) {
        this(BowlingPoint.of(point));
    }

    public Strike(BowlingPoint point) {
        this.point = point;
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
