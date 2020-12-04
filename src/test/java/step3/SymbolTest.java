package step3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import step3.domain.dto.PointDTO;
import step3.type.ResultPitchesType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static step3.type.ResultPitchesType.ERROR_INVALID_OBJECT;

public class SymbolTest {
    private PointDTO forStrike;
    private PointDTO forMiss;
    private PointDTO forGutter;
    private PointDTO forSpare;

    @BeforeEach
    void setup() {
        forStrike = new PointDTO(2, 1, 10, null, null);
        forMiss = new PointDTO(2, 2, 5, 4, null);
        forGutter = new PointDTO(2, 2, 3, 0, null);
        forSpare = new PointDTO(2, 2, 5, 5, null);
    }

    @DisplayName("심볼 생성 테스트")
    @Test
    void createStrikeSymbol() {
        ResultPitchesType strike = ResultPitchesType.getType(forStrike);
        assertThat(strike).isEqualTo(ResultPitchesType.STRIKE);

        ResultPitchesType miss = ResultPitchesType.getType(forMiss);
        assertThat(miss).isEqualTo(ResultPitchesType.MISS);

        ResultPitchesType gutter = ResultPitchesType.getType(forGutter);
        assertThat(gutter).isEqualTo(ResultPitchesType.GUTTER);

        ResultPitchesType spare = ResultPitchesType.getType(forSpare);
        assertThat(spare).isEqualTo(ResultPitchesType.SPARE);
    }

    @DisplayName("검색결과없음 기본 심볼 생성 테스트")
    @Test
    void createEmptySymbol() {
        ResultPitchesType type = ResultPitchesType.getType(new PointDTO(2, 2, 10, 4, null));
        assertThat(type).isEqualByComparingTo(ResultPitchesType.NONE);
    }

    @DisplayName("예외 테스트")
    @ParameterizedTest
    @NullSource
    void createSymbolWithNullSource(PointDTO dto) {
        assertThatIllegalArgumentException()
                .isThrownBy(()->ResultPitchesType.getType(dto))
                .withMessage(ERROR_INVALID_OBJECT);
    }
}
