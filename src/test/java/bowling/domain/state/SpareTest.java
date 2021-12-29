package bowling.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.domain.Pins;
import org.junit.jupiter.api.Test;

class SpareTest {

    @Test
    void 생성() {
        assertThat(new Spare(Pins.of(8), Pins.of(2))).isInstanceOf(Spare.class);
    }

    @Test
    void spare_아닐경우() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Spare(Pins.of(8), Pins.of(1)));
    }

    @Test
    void 완료여부() {
        assertThat(new Spare(Pins.of(8), Pins.of(2)).isFinished()).isTrue();
    }

}