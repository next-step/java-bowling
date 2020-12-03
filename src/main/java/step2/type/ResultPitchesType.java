package step2.type;

import step2.domain.Condition;
import step2.domain.SymbolSupply;
import step2.domain.dto.PointDTO;
import step2.state.*;

import java.util.Arrays;
import java.util.Objects;

public enum ResultPitchesType {
    STRIKE(StrikeSymbol::supported, StrikeSymbol::new),
    SPARE(SpareSymbol::supported, SpareSymbol::new),
    MISS(MissSymbol::supported, MissSymbol::new),
    GUTTER(GutterSymbol::supported, GutterSymbol::new),
    NONE(EmptySymbol::supported, EmptySymbol::new);

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
