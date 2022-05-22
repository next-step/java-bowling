package bowling.domain;

import static bowling.domain.Pins.DEFAULT_PIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class PinsTest {

    @Test
    void 볼링핀을_치면_친_개수만큼_서있는_개수가_줄어든다() {
        // given
        final int hitCount = 4;
        Pins pins = new Pins();
        assertThat(pins.standing()).isEqualTo(DEFAULT_PIN);

        // when
        pins.hit(hitCount);

        // then
        assertThat(pins.standing()).isEqualTo(DEFAULT_PIN - hitCount);
    }

    @Test
    void 남아있는_핀_이상의_숫자를_쳤다고_들어오면_예외발생한다() {
        // given
        final int hitCount = 11;
        Pins pins = new Pins();
        assertThat(pins.standing()).isEqualTo(DEFAULT_PIN);

        assertThatThrownBy(() -> {
            pins.hit(hitCount);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
