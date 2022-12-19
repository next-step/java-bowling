package bowling.model.state;

import bowling.model.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    Ready ready = new Ready();

    @Test
    @DisplayName("레디 상태에서 10개를 맞추면 스트라이크이다.")
    void strike() {
        assertThat(ready.bowl(Pin.of(10))).isInstanceOf(Strike.class);
    }

    @DisplayName("레디 상태에서 10개 이하 맞추면 첫번째 볼이다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    void first(int input) {
        assertThat(ready.bowl(Pin.of(input))).isInstanceOf(First.class);

    }
}