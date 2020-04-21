package bowling.domain.pitch;

import bowling.domain.exception.OutOfRangeArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PinTest {
    @DisplayName("값은 캐싱된다")
    @Test
    void cache() {
        assertThat(Pin.valueOf(10)).isEqualTo(Pin.valueOf(10));
    }

    @DisplayName("10은 최대값이다.")
    @Test
    void isMax() {
        assertThat(Pin.valueOf(10).isMax()).isTrue();
        assertThat(Pin.valueOf(9).isMax()).isFalse();
    }

    @DisplayName("0은 최소값이다.")
    @Test
    void isMin() {
        assertThat(Pin.valueOf(0).isMin()).isTrue();
        assertThat(Pin.valueOf(1).isMin()).isFalse();
    }

    @DisplayName("0~10 사이의 숫자가 아니면 에러")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void raiseOutOfRange(int count) {
        assertThatExceptionOfType(OutOfRangeArgumentException.class)
                .isThrownBy(() -> Pin.valueOf(count));
    }
}
