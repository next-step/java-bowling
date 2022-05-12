package bowling.domain.pin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinNoTest {

    @ParameterizedTest(name = "쓰러뜨린 핀 개수는 0부터 10이다.")
    @ValueSource(ints = {-1, 11})
    void validation(int no) {
        assertThatThrownBy(() -> PinNo.of(no))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void cache() {
        assertThat(PinNo.of(0)).isSameAs(PinNo.of(0));
        assertThat(PinNo.of(10)).isSameAs(PinNo.of(10));
    }

    @Test
    void isMaxNo() {
        assertThat(PinNo.of(0).isMaxNo()).isFalse();
        assertThat(PinNo.of(10).isMaxNo()).isTrue();
    }

    @Test
    void isMinNo() {
        assertThat(PinNo.of(0).isMinNo()).isTrue();
        assertThat(PinNo.of(10).isMinNo()).isFalse();
    }

    @Test
    void plus() {
        assertThat(PinNo.of(2).plus(PinNo.of(3))).isSameAs(PinNo.of(5));
    }
}
