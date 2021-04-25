package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FinalPinsTest {

    @Test
    @DisplayName("FinalPins는 Pin을 세 개 가지고 생성된다.")
    void create() {
        // given
        final Pin firstPin = new Pin();
        final Pin secondPin = new Pin();
        final Pin thirdPin = new Pin();

        // when
        final FinalPins pins = new FinalPins(firstPin, secondPin, thirdPin);

        // then
        assertAll(
                () -> assertThat(pins).isEqualTo(new FinalPins(firstPin, secondPin, thirdPin)),
                () -> assertThat(pins.firstPin()).isEqualTo(firstPin),
                () -> assertThat(pins.secondPin()).isEqualTo(secondPin),
                () -> assertThat(pins.thirdPin()).isEqualTo(thirdPin)
        );
    }
}
