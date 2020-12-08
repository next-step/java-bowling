package step4.state;

import step4.domain.BowlingPoint;
import step4.domain.dto.PointDTO;
import step4.type.ResultPitchesType;

import static step4.state.Symbol.*;

public class Spare implements Symbol {
    public static ResultPitchesType type = ResultPitchesType.SPARE;
    private final BowlingPoint point;

    public Spare(int point) {
        this(BowlingPoint.of(point));
    }

    public Spare(BowlingPoint point) {
        this.point = point;
    }

    @Override
    public String getSymbol() {
        return "/";
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
