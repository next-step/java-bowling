package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {

    @Test
    @DisplayName("핀 10개까지 쓰러트릴 수 있다.")
    void canKnockDownTenPins() {
        Pins pins = new Pins();

        pins.knockDown(new PitchResult(10));

        assertThat(pins.isAllCleared()).isTrue();
    }

    @Test
    @DisplayName("현재 남아있는 핀의 개수보다 더 많은 핀을 쓰러뜨릴 수 없다.")
    void cannotKnockDown() {
        Pins pins = new Pins();
        pins.knockDown(new PitchResult(5));

        assertThatThrownBy(() -> pins.knockDown(new PitchResult(6))).isInstanceOf(IllegalArgumentException.class);
    }
    
}
