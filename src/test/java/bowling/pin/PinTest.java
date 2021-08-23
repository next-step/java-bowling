package bowling.pin;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PinTest {
    @Test
    @DisplayName("Pin의 개수가 0 미만일 경우 예외를 발생시킨다")
    void exception_1() throws Exception {
        Assertions.assertThatThrownBy(() -> {
            Pin.from(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Pin의 개수가 10을 초과 할 경우 예외를 발생시킨다")
    void exception_2() throws Exception {
        Assertions.assertThatThrownBy(() -> {
            Pin.from(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("Pin의 합을 반환한다")
    void willReturnSum(final int e1, final int e2, final int expected) throws Exception {
        final Pin p1 = Pin.from(e1);
        final Pin p2 = Pin.from(e2);
        final Pin expectedPin = Pin.from(expected);
        Assertions.assertThat(p1.willReturnSum(p2)).isEqualTo(expectedPin);
    }

    private static Stream<Arguments> willReturnSum() {
        return Stream.of(
                Arguments.of(5, 3, 8),
                Arguments.of(5, 5, 10),
                Arguments.of(0, 1, 1)
        );
    }

    @Test
    @DisplayName("Pin의 합이 10을 초과 할 경우 예외를 발생시킨다")
    void willReturnSum_exception() throws Exception {
        final Pin p1 = Pin.from(5);
        final Pin p2 = Pin.from(6);
        Assertions.assertThatThrownBy(() -> {
            p1.willReturnSum(p2);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Pin이 10개인 경우 참을 반환한다")
    void isMaxCount() throws Exception {
        final Pin maxPin = Pin.from(Pin.MAX_COUNT_OF_PIN);
        Assertions.assertThat(maxPin.isMaxCount()).isTrue();
    }

    @Test
    @DisplayName("Pin의 개수를 int로 반환한다")
    void parseInt() throws Exception {
        final int pinCount = 5;
        final Pin pin = Pin.from(pinCount);
        Assertions.assertThat(pin.parseInt()).isEqualTo(pinCount);
    }
}
