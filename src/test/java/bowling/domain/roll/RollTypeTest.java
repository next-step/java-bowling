package bowling.domain.roll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RollTypeTest {

    @ParameterizedTest
    @CsvSource(value = {
            "10,0,STRIKE",
            "0,1,GUTTER",
            "5,0,SPARE",
            "4,2,DEFAULT"
    })
    @DisplayName("유형별 정상 생성")
    void testCreateRollType(int knocked, int remained, String rollType) {
        assertThat(RollType.valueOf(knocked, remained)).isEqualTo(RollType.valueOf(rollType));
    }
}
