package step3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import step3.domain.dto.PointDTO;
import step3.type.ResultPitchesType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ResultPitchesTypeTest {

    @DisplayName("getType 테스트")
    @Test
    void getType() {
        assertThat(ResultPitchesType.getType(new PointDTO(2,2, 1, 5, null)))
                .isEqualByComparingTo(ResultPitchesType.MISS);

        assertThat(ResultPitchesType.getType(new PointDTO(2,1, 10, 0, null)))
                .isEqualByComparingTo(ResultPitchesType.STRIKE);

        assertThat(ResultPitchesType.getType(new PointDTO(2,2, 5, 5, null)))
                .isEqualByComparingTo(ResultPitchesType.SPARE);

        assertThat(ResultPitchesType.getType(new PointDTO(2, 2, 0, 10, null)))
                .isEqualByComparingTo(ResultPitchesType.SPARE);

    }

    @DisplayName("getType 검색결과 없는 경우 반환 테스트")
    @ParameterizedTest
    @CsvSource(value = {"11,5", "5,11"})
    void getTypeWithException(int first, int second) {
        assertThatThrownBy(() -> ResultPitchesType.getType(new PointDTO(2, 1, first, second, null)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
