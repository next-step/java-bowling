package bowling.domain;

import static bowling.domain.Pins.START_PIN_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class PinsTest {

    @Test
    void 볼링핀을_치면_친_개수만큼_서있는_개수가_줄어든다() {
        // given
        final int hitCount = 4;
        Pins pins = new Pins();
        assertThat(pins.standing()).isEqualTo(START_PIN_COUNT);

        // when
        pins.hit(hitCount);

        // then
        assertThat(pins.standing()).isEqualTo(START_PIN_COUNT - hitCount);
    }

    @Test
    void 남아있는_핀_이상의_숫자를_쳤다고_들어오면_예외발생한다() {
        // given
        final int hitCount = 11;
        Pins pins = new Pins();
        assertThat(pins.standing()).isEqualTo(START_PIN_COUNT);

        assertThatThrownBy(() -> {
            pins.hit(hitCount);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 음수의_핀을_쳤다고_들어오면_예외발생한다() {
        // given
        final int hitCount = -1;
        Pins pins = new Pins();
        assertThat(pins.standing()).isEqualTo(START_PIN_COUNT);

        assertThatThrownBy(() -> {
            pins.hit(hitCount);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
