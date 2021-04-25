package bowling.domain.pin;

import bowling.domain.frame.FrameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource({"10,0,3,STRIKE", "9,1,3,SPARE", "3,4,0,MISS", "0,0,0,GUTTER"})
    @DisplayName("각 조건에 해당하는 FrameStatus가 반환된다.")
    void frameStatus(int firstPinCount, int secondPinCount, int thirdPinCount, FrameStatus expectedFrameStatus) {
        // given
        final Pin firstPin = new Pin(firstPinCount);
        final Pin secondPin = new Pin(secondPinCount);
        final Pin thirdPin = new Pin(thirdPinCount);
        final FinalPins finalPins = new FinalPins(firstPin, secondPin, thirdPin);

        // when
        final FrameStatus frameStatus = finalPins.frameStatus();

        // then
        assertThat(frameStatus).isEqualTo(expectedFrameStatus);
    }
}
