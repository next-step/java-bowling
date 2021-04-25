package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NormalPinsTest {

    @Test
    @DisplayName("NormalPins는 Pin을 두 개 가지고 생성된다.")
    void create() {
        // given
        final Pin firstPin = new Pin();
        final Pin secondPin = new Pin();

        // when
        final Pins pins = new NormalPins(firstPin, secondPin);

        // then
        assertAll(
                () -> assertThat(pins).isEqualTo(new NormalPins(firstPin, secondPin)),
                () -> assertThat(pins.firstPin()).isEqualTo(firstPin),
                () -> assertThat(pins.secondPin()).isEqualTo(secondPin)
        );
    }
}
