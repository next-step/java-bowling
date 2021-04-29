package bowling.domain.pin;

import bowling.domain.TestFixture;
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
        final Pin firstPin = TestFixture.STRIKE_PIN;
        final Pin secondPin = new Pin(0);
        final Pin thirdPin = TestFixture.STRIKE_PIN;

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
    @CsvSource({"10,0,3,NORMAL", "9,1,3,NORMAL", "3,4,0,NORMAL", "0,0,0,NORMAL"})
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

    @Test
    @DisplayName("쓰러진 핀을 전달받으면 새로운 Pins가 반환된다.")
    void knockDownPin() {
        // given
        final FinalPins beforePins = new FinalPins();
        final FinalPins strikePins = new FinalPins(TestFixture.STRIKE_PIN);

        // when
        final Pins afterPins = beforePins.knockDownPin(TestFixture.STRIKE_PIN);

        // then
        assertThat(afterPins).isEqualTo(strikePins);
    }
}
