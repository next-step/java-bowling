package bowling.domain.value;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinsTest {

    @Test
    void createTest() {
        Pins pins = new Pins(3);

        assertThat(pins.getPins()).isEqualTo(3);
    }

    @Test
    @DisplayName("볼링핀의 입력 범위 예외 검증")
    void exceptionTest() {
        assertThatThrownBy(() ->
                new Pins(100)).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() ->
                new Pins(-6)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("핀을 10개 치면 스트라이크 인지 아닌지 검증")
    void isStrikeTest() {
        Pins pins = new Pins(10);

        assertThat(pins.isStrike()).isTrue();
    }

    @Test
    @DisplayName("핀을 0개 치면 스트라이크 인지 아닌지 검증")
    void isGutterTest() {
        Pins pins = new Pins(0);

        assertThat(pins.isGutter()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("핀을 여러개 치면 스트라이크와 거터가 아닌지 검증")
    @ValueSource(ints = {2,3,4})
    void isNormalTest(int value) {
        Pins pins = new Pins(value);

        assertThat(pins.isGutter()).isFalse();
        assertThat(pins.isStrike()).isFalse();
    }
}
