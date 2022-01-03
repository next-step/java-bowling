package bowling.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.domain.Pins;
import org.junit.jupiter.api.Test;

class FirstBowlTest {

    @Test
    void mess_객체생성() {
        assertThat(new FirstBowl(Pins.of(2)).bowl(Pins.of(2))).isInstanceOf(Mess.class);
    }

    @Test
    void spare_객체생성() {
        assertThat(new FirstBowl(Pins.of(2)).bowl(Pins.of(8))).isInstanceOf(Spare.class);
    }

    @Test
    void strike_객체생성하려할시_예외처리() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new FirstBowl(Pins.of(10)));
    }

    @Test
    void 완료여부() {
        assertThat(new FirstBowl(Pins.of(2)).isFinished()).isFalse();
    }

}