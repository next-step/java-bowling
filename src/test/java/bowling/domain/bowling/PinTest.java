package bowling.domain.bowling;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PinTest {

    @ParameterizedTest
    @ValueSource(ints = {30, 11, 20})
    @DisplayName("핀은 0~10번의 값이 들어오지 않으면 Exception이 발생해야 한다.")
    void pinSaveFailTest(int input) {

        // when & then
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> Pin.of(input))
            .withMessageMatching("핀은 0~10 사이의 값만 저장할 수 있다.");
    }

}