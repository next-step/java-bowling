package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("핀")
class PinsTest {

    @ParameterizedTest(name = "[{index}] {0} 으로 생성")
    @DisplayName("0 과 10 사이의 숫자로 생성")
    @ValueSource(ints = {0, 5, 10})
    void instance(int count) {
        assertThatNoException().isThrownBy(() -> Pins.from(count));
    }

    @ParameterizedTest(name = "[{index}] {0} 으로 생성 불가능")
    @DisplayName("숫자는 반드시 0 과 10 사이")
    @ValueSource(ints = {-1, 11})
    void instance_outOfRange_thrownIllegalArgumentException(int count) {
        assertThatIllegalArgumentException().isThrownBy(() -> Pins.from(count));
    }

    @Test
    @DisplayName("합")
    void sum() {
        assertThat(Pins.from(1).sum(Pins.from(2))).isEqualTo(Pins.from(3));
    }

    @Test
    @DisplayName("두 핀들의 합이 10 이상일 수 없음")
    void sum_overMax_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Pins.MAX.sum(Pins.from(1)));
    }

    @Test
    @DisplayName("빼기")
    void minus() {
        assertThat(Pins.from(2).minus(Pins.from(1))).isEqualTo(Pins.from(1));
    }

    @Test
    @DisplayName("더 큰 값을 뺄 수 없음")
    void minus_greaterThan_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Pins.ZERO.minus(Pins.MAX));
    }

    @Test
    @DisplayName("주어진 갯수 그대로 반환")
    void count() {
        assertThat(Pins.ZERO.count()).isZero();
    }
}
