package step3.state;

import step3.domain.dto.PointDTO;
import step3.type.ResultPitchesType;

import static step3.state.Symbol.*;

public class Spare implements Symbol {
    public static ResultPitchesType type = ResultPitchesType.SPARE;
    private final int point;

    public Spare(int point) {
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
