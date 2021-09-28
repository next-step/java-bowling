package bowling.domain.pin;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PinTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 15, 100})
    @DisplayName("pin은 0~10 사이의 값만 저장할 수 있다.")
    void pinSaveRangeExceptionTest(int input) {

        // when & then
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> Pin.of(input))
            .withMessageMatching("Pin은 0 ~ 10 사이의 수만 저장될 수 있습니다.");
    }

}