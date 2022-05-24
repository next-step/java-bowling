package bowling.domain;

import static bowling.domain.Pins.START_PIN_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void 유효하지_않은_숫자를_쳤다고_들어오면_예외발생한다(int invalidHitCount) {
        // given
        Pins pins = new Pins();
        assertThat(pins.standing()).isEqualTo(START_PIN_COUNT);

        assertThatThrownBy(() -> {
            pins.hit(invalidHitCount);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
