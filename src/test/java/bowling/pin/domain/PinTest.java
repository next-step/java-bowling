package bowling.pin.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PinTest {

    @Test
    @DisplayName("넘어진 볼링핀 생성 테스트")
    void of() {
        assertThat(Pin.of(1)).isEqualTo(Pin.of(1));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("볼링핀의 범위는 0~11개 이다.")
    void exception(int felled) {
        assertThatIllegalArgumentException().isThrownBy(() -> Pin.of(felled));
    }

    @Test
    @DisplayName("볼링핀 덧셈 - 1개의 넘어진 핀과 3개의 넘어진 핀을 더하면 4개의 핀이 된다.")
    void add() {
        assertThat(Pin.of(1).add(Pin.of(3))).isEqualTo(Pin.of(4));
    }

    @Test
    @DisplayName("넘어진 볼링핀의 합은 10을 넘을 수 없다.")
    void addException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Pin.of(9).add(Pin.of(3)));
    }

}
