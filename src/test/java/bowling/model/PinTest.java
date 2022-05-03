package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayName("핀")
class PinTest {

    @ParameterizedTest(name = "[{index}] {0} 으로 생성")
    @DisplayName("0 과 10 사이의 숫자로 생성")
    @ValueSource(ints = {0, 5, 10})
    void instance(int count) {
        assertThatNoException().isThrownBy(() -> Pin.from(count));
    }

    @ParameterizedTest(name = "[{index}] {0} 으로 생성 불가능")
    @DisplayName("숫자는 반드시 0 과 10 사이")
    @ValueSource(ints = {-1, 11})
    void instance_outOfRange_thrownIllegalArgumentException(int count) {
        assertThatIllegalArgumentException().isThrownBy(() -> Pin.from(count));
    }
}
