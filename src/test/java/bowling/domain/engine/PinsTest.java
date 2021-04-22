package bowling.domain.engine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {

    @Test
    @DisplayName("핀 10개까지 쓰러트릴 수 있다.")
    void canKnockDownTenPins() {
        Pins pins = new Pins();

        pins.knockDown(PitchResult.wrap(10));

        assertThat(pins.isAllCleared()).isTrue();
    }

    @Test
    @DisplayName("현재 남아있는 핀의 개수보다 더 많은 핀을 쓰러뜨릴 수 없다.")
    void cannotKnockDown() {
        Pins pins = new Pins();
        pins.knockDown(PitchResult.wrap(5));

        assertThatThrownBy(() -> pins.knockDown(PitchResult.wrap(6))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("리셋하면 핀의 개수를 다시 10 개로 되돌린다.")
    void resetPins() {
        Pins pins = new Pins();
        pins.knockDown(PitchResult.wrap(10));
        assertThat(pins.isAllCleared()).isTrue();

        pins.reset();
        assertThat(pins.isAllCleared()).isFalse();
    }

}
