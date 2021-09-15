package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PinCountTest {

    @DisplayName("넘어진 핀의 수는 0에서 10개 사이의 값이다. - 성공 테스트")
    @Test
    public void createFallenPinCountSuccessTest() {
        assertThat(PinCount.valueOf(0)).isEqualTo(PinCount.ZERO);
        assertThat(PinCount.valueOf(5)).isEqualTo(PinCount.FIVE);
        assertThat(PinCount.valueOf(10)).isEqualTo(PinCount.TEN);
    }

    @ParameterizedTest(name = "넘어진 핀의 수는 0에서 10개 사이의 값이다. - 실패 테스트")
    @ValueSource(ints = {-1, 11})
    public void createFallenPinCountFailTest(int countInput) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> PinCount.valueOf(countInput))
                .withMessageContaining(String.valueOf(countInput));
    }

    @ParameterizedTest(name = "스페어 테스트")
    @CsvSource({"SEVEN, THREE, true", "SEVEN, TWO, false", "TEN, ZERO, false", "ZERO, TEN, true"})
    public void spareTest(PinCount first, PinCount second, boolean expected) {
        assertThat(second.spare(first)).isEqualTo(expected);
    }
}