package bowling.domain.pin;

import bowling.domain.TestFixture;
import bowling.domain.frame.FrameStatus;
import bowling.exception.PinsCountExceededException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class PinsTest {

    @Test
    @DisplayName("create로 Pins를 생성하면 비어있는 Pins가 생성된다.")
    void createEmptyPins() {
        // given
        // when
        final Pins pins = Pins.create();

        // then
        assertThat(pins).isEqualTo(Pins.from(new ArrayList<>()));
    }

    @Test
    @DisplayName("Pins는 Pin 두 개로 생성할 수 있다.")
    void createOfTwoPin() {
        // given
        final Pin firstPin = TestFixture.STRIKE_PIN;
        final Pin secondPin = TestFixture.GUTTER_PIN;

        // when
        final Pins pins = Pins.of(firstPin, secondPin);

        // then
        assertAll(
                () -> assertThat(pins).isEqualTo(Pins.of(firstPin, secondPin)),
                () -> assertThat(pins.firstPin()).isEqualTo(firstPin),
                () -> assertThat(pins.secondPin()).isEqualTo(secondPin)
        );
    }

    @Test
    @DisplayName("Pins는 Pin 세 개로 생성할 수 있다.")
    void createOfThreePin() {
        // given
        final Pin firstPin = TestFixture.STRIKE_PIN;
        final Pin secondPin = TestFixture.GUTTER_PIN;
        final Pin thirdPin = TestFixture.STRIKE_PIN;

        // when
        final Pins pins = Pins.of(firstPin, secondPin, thirdPin);

        // then
        assertAll(
                () -> assertThat(pins).isEqualTo(Pins.of(firstPin, secondPin, thirdPin)),
                () -> assertThat(pins.firstPin()).isEqualTo(firstPin),
                () -> assertThat(pins.secondPin()).isEqualTo(secondPin),
                () -> assertThat(pins.thirdPin()).isEqualTo(thirdPin)
        );
    }

    @Test
    @DisplayName("Pins는 4개의 Pin으로 생성할 수 없다.")
    void createOfFourPin() {
        // given
        final Pin firstPin = TestFixture.STRIKE_PIN;
        final Pin secondPin = TestFixture.GUTTER_PIN;
        final Pin thirdPin = TestFixture.STRIKE_PIN;
        final Pin fourthPin = TestFixture.STRIKE_PIN;

        // when
        // then
        assertThatThrownBy(() -> Pins.of(firstPin, secondPin, thirdPin, fourthPin))
                .isInstanceOf(PinsCountExceededException.class)
                .hasMessage(PinsCountExceededException.PINS_COUNT_EXCEEDED);
    }

    @ParameterizedTest
    @CsvSource({"10,0,STRIKE", "9,1,SPARE", "3,4,NORMAL", "0,0,MISS"})
    @DisplayName("각 조건에 해당하는 FrameStatus가 반환된다.")
    void frameStatus(int firstPinCount, int secondPinCount, FrameStatus expectedFrameStatus) {
        // given
        final Pin firstPin = new Pin(firstPinCount);
        final Pin secondPin = new Pin(secondPinCount);
        final Pins pins = Pins.of(firstPin, secondPin);

        // when
        final FrameStatus frameStatus = pins.frameStatus();

        // then
        assertThat(frameStatus).isEqualTo(expectedFrameStatus);
    }

    @ParameterizedTest
    @CsvSource({"10,0,3,NORMAL", "9,1,3,NORMAL", "3,4,0,NORMAL", "0,0,0,NORMAL"})
    @DisplayName("각 조건에 해당하는 FrameStatus가 반환된다.")
    void frameStatus(int firstPinCount, int secondPinCount, int thirdPinCount, FrameStatus expectedFrameStatus) {
        // given
        final Pin firstPin = new Pin(firstPinCount);
        final Pin secondPin = new Pin(secondPinCount);
        final Pin thirdPin = new Pin(thirdPinCount);
        final Pins pins = Pins.of(firstPin, secondPin, thirdPin);

        // when
        final FrameStatus frameStatus = pins.frameStatus();

        // then
        assertThat(frameStatus).isEqualTo(expectedFrameStatus);
    }

    @Test
    @DisplayName("쓰러진 핀을 전달받으면 Pins의 상태가 변경된다.")
    void knockDownPin() {
        // given
        final Pins pins = Pins.create();

        // when
        pins.knockDownPin(TestFixture.STRIKE_PIN);

        // then
        assertThat(pins).isEqualTo(Pins.of(TestFixture.STRIKE_PIN));
    }

    @Test
    @DisplayName("Pins는 3개의 Pin까지만 쓰러뜨릴 수 있다.")
    void knockDownMorePinWhenThreePins() {
        // given
        final Pin firstPin = TestFixture.STRIKE_PIN;
        final Pin secondPin = TestFixture.GUTTER_PIN;
        final Pin thirdPin = TestFixture.STRIKE_PIN;
        final Pins pins = Pins.of(firstPin, secondPin, thirdPin);

        // when
        // then
        assertThatThrownBy(() -> pins.knockDownPin(TestFixture.STRIKE_PIN))
                .isInstanceOf(PinsCountExceededException.class)
                .hasMessage(PinsCountExceededException.PINS_COUNT_EXCEEDED);
    }

    @Test
    @DisplayName("전체 핀의 개수를 리턴한다.")
    void totalPinCount() {
        // given
        final Pins pins = Pins.of(TestFixture.STRIKE_PIN, TestFixture.GUTTER_PIN);

        // when
        final int totalPinCount = pins.totalPinCount();

        // then
        assertThat(totalPinCount).isEqualTo(10);
    }
}
