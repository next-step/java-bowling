package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

class PinTest {

    @Test
    @DisplayName("핀 갯수를 반환")
    void getPin() {
        int expected = 3;

        then(Pin.of(expected).getPin()).isEqualTo(expected);
    }

    @Test
    @DisplayName("최솟값을 가지는 인스턴스를 반환")
    void ofMin() {
        Pin expected = Pin.of(Pin.MIN_PINS);

        then(Pin.ofMin()).isEqualTo(expected);
    }

    @Test
    @DisplayName("최댓값을 가지는 인스턴스를 반환")
    void ofMax() {
        Pin expected = Pin.of(Pin.MAX_PINS);

        then(Pin.ofMax()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"-1, 11"})
    @DisplayName("인스턴스 반환 간 예외 처리")
    void of(int pin) {
        assertThatThrownBy(() -> Pin.of(pin))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 핀 갯수가 범위를 초과했습니다.");
    }

    @Test
    @DisplayName("equals() 검증")
    void testEquals() {
        Pin pin01 = Pin.of(2);
        Pin pin02 = Pin.of(2);
        Pin pin03 = Pin.of(3);

        then(pin01.equals(pin02)).isTrue();
        then(pin01.equals(pin03)).isFalse();
    }

    @Test
    @DisplayName("hashCode() 검증")
    void testHashCode() {
        int expected = 2;

        then(Pin.of(expected).hashCode()).isEqualTo(expected);
    }

    @Test
    @DisplayName("두 핀의 합이 최댓값 보다 큰지 검증")
    void isOverMaxPins() {
        Pin max = Pin.ofMax();
        Pin min = Pin.ofMin();
        Pin one = Pin.of(1);

        then(max.isOverMaxPins(min)).isFalse();
        then(max.isOverMaxPins(one)).isTrue();
    }

    @Test
    @DisplayName("두 핀의 합이 최댓값과 같은지 검증")
    void isSpare() {
        Pin max = Pin.ofMax();
        Pin min = Pin.ofMin();
        Pin one = Pin.of(1);

        then(max.isSpare(min)).isTrue();
        then(max.isSpare(one)).isFalse();
    }
}
