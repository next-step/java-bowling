package step4.type;

import step4.domain.Condition;
import step4.domain.SymbolSupply;
import step4.domain.dto.PointDTO;
import step4.state.*;

import java.util.Arrays;
import java.util.Objects;

public enum ResultPitchesType {
    STRIKE(Strike::supported, Strike::new),
    SPARE(Spare::supported, Spare::new),
    MISS(Miss::supported, Miss::new),
    GUTTER(Gutter::supported, Gutter::new),
    NONE(Empty::supported, Empty::new);

    public static final String ERROR_INVALID_OBJECT = "유효하지 않은 객체입니다.";
    private final Condition condition;
    private final SymbolSupply supply;

    ResultPitchesType(Condition condition, SymbolSupply supply) {
        this.condition = condition;
        this.supply = supply;
    }

    public static ResultPitchesType getType(PointDTO dto) {
        isValidDto(dto);
        return Arrays.stream(ResultPitchesType.values())
                .filter(value->value.condition.filter(dto))
                .findFirst()
                .orElse(NONE);
    }

    private static void isValidDto(PointDTO dto) {
        if (Objects.isNull(dto)) {
            throw new IllegalArgumentException(ERROR_INVALID_OBJECT);
        }
    }

    public static Symbol createSymbol(PointDTO dto) {
        int currentSize = dto.getCurrentSize();
        ResultPitchesType type = getType(dto);

        if (currentSize == 1) {
            return type.supply.create(dto.getFirst());
        }

        if (currentSize == 2) {
            return type.supply.create(dto.getSecond());
        }

        return type.supply.create(dto.getThird());
    }
}
