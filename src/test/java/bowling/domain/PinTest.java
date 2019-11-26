package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinTest {

    @Test
    @DisplayName("Ball은 10까지만 pin을 가질 수 있다.")
    void pinIsUnderTen() {
        assertThatThrownBy(() -> {
            Pin pin = Pin.ofDefault();
            pin.fallDown(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource(value = "providePinCount")
    @DisplayName("pin의 갯수가 10개면 isStrike결과가 true인지 확인한다.")
    void isStrike(int pinCount, boolean result) {
        Pin expectStrikePin = Pin.ofDefault();
        expectStrikePin.fallDown(pinCount);

        assertThat(expectStrikePin.isStrike()).isEqualTo(result);
    }

    static Stream<Arguments> providePinCount() {
        return Stream.of(
                Arguments.of(10, true),
                Arguments.of(5, false)
        );
    }
}
