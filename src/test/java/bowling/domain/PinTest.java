package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("핀 테스트")
class PinTest {

    @DisplayName("0 미만 또는 10 초과의 수가 입력되면 예외가 발생한다.")
    @ParameterizedTest(name = "{displayName} 입력값={0}")
    @ValueSource(ints = {-1, 11})
    void pin(int input) {
        assertThatThrownBy(() -> new Pin(input));
    }

    @DisplayName("캐싱된 데이터를 사용할 수 있다.")
    @Test
    void cache() {
        assertThat(Pin.of(1)).isEqualTo(Pin.of(1));
    }
}
