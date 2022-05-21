package bowling.domain.pin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinTest {

    @ParameterizedTest(name = "쓰러뜨린 핀 개수는 0부터 10이다.")
    @ValueSource(ints = {-1, 11})
    void validation(int no) {
        assertThatThrownBy(() -> Pin.of(no))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void cache() {
        assertThat(Pin.of(0)).isSameAs(Pin.of(0));
        assertThat(Pin.of(10)).isSameAs(Pin.of(10));
    }

    @Test
    void isSpareWith() {
        assertThat(Pin.of(5).isSpareWith(Pin.of(5))).isTrue();
        assertThat(Pin.of(5).isSpareWith(Pin.of(4))).isFalse();
    }

    @Test
    void isMissWith() {
        assertThat(Pin.of(5).isMissWith(Pin.of(5))).isFalse();
        assertThat(Pin.of(5).isMissWith(Pin.of(4))).isTrue();
    }

    @Test
    void sum() {
        assertThat(Pin.of(5).sum(Pin.of(4))).isEqualTo(9);
        assertThat(Pin.of(5).sum(4)).isEqualTo(9);
    }
}
