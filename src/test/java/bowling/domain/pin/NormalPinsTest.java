package bowling.domain.pin;

import bowling.domain.frame.FrameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NormalPinsTest {

    @Test
    @DisplayName("NormalPins는 Pin을 두 개 가지고 생성된다.")
    void create() {
        // given
        final Pin firstPin = new Pin();
        final Pin secondPin = new Pin(0);

        // when
        final Pins pins = new NormalPins(firstPin, secondPin);

        // then
        assertAll(
                () -> assertThat(pins).isEqualTo(new NormalPins(firstPin, secondPin)),
                () -> assertThat(pins.firstPin()).isEqualTo(firstPin),
                () -> assertThat(pins.secondPin()).isEqualTo(secondPin)
        );
    }

    @ParameterizedTest
    @CsvSource({"10,0,STRIKE", "9,1,SPARE", "3,4,MISS", "0,0,GUTTER"})
    @DisplayName("각 조건에 해당하는 FrameStatus가 반환된다.")
    void frameStatus(int firstPinCount, int secondPinCount, FrameStatus expectedFrameStatus) {
        // given
        final Pin firstPin = new Pin(firstPinCount);
        final Pin secondPin = new Pin(secondPinCount);
        final NormalPins normalPins = new NormalPins(firstPin, secondPin);

        // when
        final FrameStatus frameStatus = normalPins.frameStatus();

        // then
        assertThat(frameStatus).isEqualTo(expectedFrameStatus);
    }
}
